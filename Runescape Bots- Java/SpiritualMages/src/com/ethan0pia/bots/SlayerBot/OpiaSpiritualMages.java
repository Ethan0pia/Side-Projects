package com.ethan0pia.bots.SlayerBot;

import com.ethan0pia.bots.SlayerBot.root.Root;
import com.ethan0pia.bots.SlayerBot.root.Utility;
import com.ethan0pia.bots.SlayerBot.ui.SlayerUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
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

public class OpiaSpiritualMages extends TreeBot implements InventoryListener, EmbeddableUI {


    private boolean bankBool = true, getFood = false, alch=true, toAlch=false;
    private int whenToEat = 50, gpGained=0;
    private SpriteItem itemToAlch;
    private Player player;
    private LinkedHashMap<Integer, Integer> priceMap = new LinkedHashMap<>();
    private Root root;
    private Utility utils = new Utility(this);
    private SlayerUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;
    private StopWatch stopWatch = new StopWatch();

    //commonly alched items from market watch
    private List<Integer> highAlchItems = new ArrayList<>(Arrays.asList(859,1073,1079,1091,1093,1111,1113,1121,1123,1125,1127,1135,1147,1149,1161,1163,1183,1185
            ,1201,1213,1215,1247,1249,1275,1289,1303,1305,1319,1333,1345,1347,1359,1373,1377,1393,1395,1397,1399,1401,1403,1405
            ,1407,1432,1434,1664,2366,2487,2489,2501,2503,2572,3053,3122,3202,3385,3387,3391,4087,4091,4093,4101,4103,4111,4113
            ,4131,4149,4153,4585,4587,6128,6129,6130,6131,6809,9185,9245,9342,9431,10083,10589,11118,11126,11133,11200,11235,11732
            ,12997,13000,13003,13006,13290,21369,22498,24365,24384,25564,25638,25666,25686,25704,25722,25737,25776,25789,25814,25929));



    public OpiaSpiritualMages() {
        // Set this class as the EmbeddableUI
        setEmbeddableUI(this);

        //add charms and coins to the price map.
        priceMap.put(995,1);
        priceMap.put(12158,0);
        priceMap.put(12159,0);
        priceMap.put(12160,0);
        priceMap.put(12163,0);
    }

    @Override
    public void onStart(String... args) {

        setLoopDelay(500, 750);
        //update timer of UI
        new LoopingThread(() -> Platform.runLater(() -> update()), 1000).start();
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
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new SlayerUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        if (event != null && player!=null && !Bank.isOpen() && !utils.amIatBank()) {
            gpGained += (priceMap.getOrDefault(event.getItem().getId(), 0) * event.getQuantityChange());
            if (isAlch() && highAlchItems.contains(event.getItem().getId())) {
                toAlch = true;
                itemToAlch=event.getItem();
            }
        }
    }

    private void update() {
        if (infoUI != null && stopWatch.getRuntimeAsString() != null) {
            infoUI.update3(stopWatch.getRuntimeAsString());
        }
    }

    public Utility getUtils() {
        return utils;
    }

    public Root getRoot() {
        return root;
    }

    public boolean isToAlch() {
        return toAlch;
    }

    public SpriteItem getItemToAlch() {
        return itemToAlch;
    }

    public boolean isAlch() {
        return alch;
    }

    public void setAlch(boolean alch) {
        this.alch = alch;
    }

    public int getWhenToEat() {
        return whenToEat;
    }

    public boolean isGetFood() {
        return getFood;
    }

    public void setGetFood(boolean getFood) {
        this.getFood = getFood;
    }

    public void setWhenToEat(int whenToEat) {
        this.whenToEat = whenToEat;
    }

    public boolean isBankBool() {
        return bankBool;
    }

    public void setBankBool(boolean bankBool) {
        this.bankBool = bankBool;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void put(int id, int value) {
        priceMap.put(id, value);
    }

    public int getGpGained() {
        return gpGained;
    }

    public SlayerUI getInfoUI() {
        return infoUI;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public boolean getPriceMap(int id){
        return priceMap.containsKey(id);
    }

    public int getValue(int id){
        return priceMap.getOrDefault(id,0);
    }

    public void setGpGained(int gpGained) {
        this.gpGained = gpGained;
    }
}