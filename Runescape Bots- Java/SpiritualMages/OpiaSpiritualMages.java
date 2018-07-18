package com.ethan0pia.bots.SpiritualMages;

import com.ethan0pia.bots.SpiritualMages.root.Root;
import com.ethan0pia.bots.SpiritualMages.root.Utility;
import com.ethan0pia.bots.SpiritualMages.ui.SpiritualMagesUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.listeners.events.ItemEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class OpiaSpiritualMages extends TreeBot implements InventoryListener, EmbeddableUI {


    private boolean needBank = false, go = false, countItems = true, stopBot = false, usingAmmo = false, worldHop = false;
    private int whenToEat = 50, gpGained=0, bankPreset = 1, combatBar = 1, hops = 0, hour = 0;
    private Player player;
    private Root root;
    private Utility utils = new Utility(this);
    private SpiritualMagesUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;
    private StopWatch stopWatch = new StopWatch();
    private Area bank=new Area.Circular(new Coordinate(2725,3492,0),8);
    private List<SpriteItem> itemsToAlch = new ArrayList<SpriteItem>();
    private String currentTask = "In UI", teleport, weapon;
    private List<GroundItem> groundItems = new ArrayList<>();

    //commonly alched items from market watch
    /*
    private List<Integer> highAlchItems = new ArrayList<>(Arrays.asList(859,1073,1079,1091,1093,1111,1113,1121,1123,1125,1127,1135,1147,1149,1161,1163,1183,1185
            ,1201,1213,1215,1247,1249,1275,1289,1303,1305,1319,1333,1345,1347,1359,1373,1377,1393,1395,1397,1399,1401,1403,1405
            ,1407,1432,1434,1664,2366,2487,2489,2501,2503,2572,3053,3122,3202,3385,3387,3391,4087,4091,4093,4101,4103,4111,4113
            ,4131,4149,4153,4585,4587,6128,6129,6130,6131,6809,9185,9245,9342,9431,10083,10589,11118,11126,11133,11200,11235,11732
            ,12997,13000,13003,13006,13290,21369,22498,24365,24384,25564,25638,25666,25686,25704,25722,25737,25776,25789,25814,25929));
    */

    //take care of all this price searching crap at the beginning to cut down on cpu load when actually running.
    private static List<Integer> highAlchIds = new ArrayList<>(Arrays.asList(1306,11732,1397,1399,1393,1395,25737,1303,1305,1249,1127,1128,2366,1149));
    private static final Map<Integer,Integer> highAlchItems = createMap();
    private static Map<Integer,Integer> createMap()
    {
        Map<Integer,Integer> myMap = new HashMap<>();
        for(Integer i : highAlchIds){
            myMap.put(i, ItemDefinition.get(i).getHighLevelAlchemyValue());
        }
        return myMap;
    }

    private static List<Integer> groundItemIds = new ArrayList<>(Arrays.asList(574,29863,566,1443,1445,1392,32341,28547,
            28548,28549,25354,20121,20122,20123,20124,1618,987,985,1631,1632,44,9342,1441,378,384,533,537,454,445,450,2362,
            2362,452,2364,220,3052,3025,2435,5302,5303,5316,5289,1780,1776,1762,1516,1516,8781,8783,20667,39814));
    private static final Map<Integer,Integer> priceMap = createMapGe();
    private static Map<Integer,Integer> createMapGe()
    {
        Map<Integer,Integer> myMap = new HashMap<>();
        for(Integer i : groundItemIds){
            int price = 0;
            ItemDefinition itemDef = ItemDefinition.get(i);
            if(itemDef!=null && itemDef.isNoted()) {
                price = GrandExchange.lookup(itemDef.getUnnotedId()).getPrice();
            }else if(itemDef!=null && itemDef.isTradeable()){
                price = GrandExchange.lookup(i).getPrice();
            }
            myMap.put(i, price);
        }
        myMap.put(995,1);
        myMap.put(12158,0);
        myMap.put(12159,0);
        myMap.put(12160,0);
        myMap.put(12163,0);
        return myMap;
    }


    public OpiaSpiritualMages() {
        // Set this class as the EmbeddableUI
        setEmbeddableUI(this);
        //add charms and coins to the price map.
    }

    @Override
    public void onStart(String... args) {

        setLoopDelay(400, 600);
        //update timer of UI
        new LoopingThread(() -> Platform.runLater(this::update), 1000).start();
        getEventDispatcher().addListener(this);
    }

    @Override
    public TreeTask createRootTask() {
        root= new Root(this);
        return root;
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new SpiritualMagesUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        if (event != null && player!=null && !bank.contains(player) && countItems && stopWatch.getRuntime(TimeUnit.SECONDS)>30) {
            if (highAlchItems.containsKey(event.getItem().getId())) {
                itemsToAlch.add(event.getItem());
            }else {
                gpGained += (priceMap.getOrDefault(event.getItem().getId(), 0) * event.getQuantityChange());
            }
        }
    }

    private void update() {
        if (infoUI != null && stopWatch.getRuntimeAsString() != null) {
            infoUI.update3(stopWatch.getRuntimeAsString());
        }
    }

    public int alchItemLength(){return itemsToAlch.size();}

    public boolean isUsingAmmo() {
        return usingAmmo;
    }

    public void setUsingAmmo(boolean usingAmmo) {
        this.usingAmmo = usingAmmo;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public boolean isStopBot() {
        return stopBot;
    }

    public void setStopBot(boolean stopBot) {
        this.stopBot = stopBot;
    }

    public void setCountItems(boolean countItems) {
        this.countItems = countItems;
    }

    public int getBankPreset() {
        return bankPreset;
    }

    public void setBankPreset(int bankPreset) {
        this.bankPreset = bankPreset;
    }

    public int getCombatBar() {
        return combatBar;
    }

    public void setCombatBar(int combatBar) {
        this.combatBar = combatBar;
    }

    public String getTeleport() {
        return teleport;
    }

    public void setTeleport(String teleport) {
        this.teleport = teleport;
    }

    public void setGo(boolean go) {
        this.go = go;
    }

    public boolean getGo(){return go;}

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public Utility getUtils() {
        return utils;
    }

    public Root getRoot() {
        return root;
    }

    public boolean isToAlch() {
        return !itemsToAlch.isEmpty();
    }

    public SpriteItem getItemToAlch() {
        return itemsToAlch.get(0);
    }

    public void removeItemToAlch(){itemsToAlch.remove(0);}

    public boolean isGroundItemsEmpty(){
        if(groundItems.isEmpty()){
            return true;
        }
        for(int i=0;i<groundItems.size();i++){
            if(groundItems.get(0).isValid()){
                return false;
            }else{
                groundItems.remove(0);
            }
        }
        return true;
    }

    public void clearGroundItems(){
        groundItems.clear();
    }

    public GroundItem getGroundItem() {
        return groundItems.get(0);
    }

    public void setGroundItems(List<GroundItem> groundItems){
        this.groundItems=groundItems;
    }

    public int getWhenToEat() {
        return whenToEat;
    }

    public void setWhenToEat(int whenToEat) {
        this.whenToEat = whenToEat;
    }

    public boolean isNeedBank() {
        return needBank;
    }

    public void setNeedBank(boolean needBank) {
        this.needBank = needBank;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGpGained() {
        return gpGained;
    }

    public SpiritualMagesUI getInfoUI() {
        return infoUI;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public boolean priceMapContains(int id){
        return highAlchItems.containsKey(id) || priceMap.containsKey(id);
    }

    public int getValue(int id){
        return priceMap.getOrDefault(id,0);
    }

    public int getAlchValue(int id){
        return highAlchItems.getOrDefault(id,0);
    }

    public void setGpGained(int gpGained) {
        this.gpGained = gpGained;
    }

    public boolean isWorldHop() {
        return worldHop;
    }

    public void setWorldHop(boolean worldHop) {
        this.worldHop = worldHop;
    }

    public int getHops() {
        return hops;
    }

    public void setHops(int hops) {
        this.hops = hops;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}