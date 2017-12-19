package com.ethan0pia.bots.GargoyleBot;

import com.ethan0pia.bots.GargoyleBot.ui.GargUI;
import com.ethan0pia.bots.GargoyleBot.branches.Root;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.listeners.events.ItemEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GargSlayer extends TreeBot implements InventoryListener, EmbeddableUI {


    private boolean bankBool = false, guiWait=true, toAlch=false, usingMagic;
    private int  whenToEat = 50, gpGained=0;
    private SpriteItem itemToAlch;
    private Player player;
    private LinkedHashMap<Integer, Integer> priceMap = new LinkedHashMap<>();
    private GargUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;
    private StopWatch stopWatch = new StopWatch();
    private List<Integer> highAlchItems = new ArrayList<>(Arrays.asList(4153,1163,1079,25737,1303));
    private String foodType="Lobster";

    public GargSlayer() {
        // Set this class as the EmbeddableUI
        setEmbeddableUI(this);
        //add drops to price map.
        priceMap.put(995,1);
        priceMap.put(12158,0);
        priceMap.put(12160,0);
        priceMap.put(12163,0);
        priceMap.put(2971,1055);
        priceMap.put(4153,29050);
        priceMap.put(4101,70224);
        priceMap.put(892,144);
        priceMap.put(1163,20180);
        priceMap.put(1079,37450);
        priceMap.put(2360,1138);
        priceMap.put(2354,505);
        priceMap.put(2362,2971);
        priceMap.put(25737,18250);
        priceMap.put(1303,18250);
        priceMap.put(37227,129);
    }

    @Override
    public void onStart(String... args) {

        setLoopDelay(300, 500);
        //update timer of UI
        new LoopingThread(() -> Platform.runLater(() -> update()), 1000).start();
        getEventDispatcher().addListener(this);
    }

    @Override
    public TreeTask createRootTask() {
        return new Root(this);
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new GargUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        if (event != null && player!=null && !Bank.isOpen()) {
            gpGained += (priceMap.getOrDefault(event.getItem().getId(), 0) * event.getQuantityChange());
            if (highAlchItems.contains(event.getItem().getId())) {
                toAlch = true;
                itemToAlch=event.getItem();
            }
        }
    }



    public boolean isToAlch() {
        return toAlch;
    }

    public SpriteItem getItemToAlch() {
        return itemToAlch;
    }

    private void update() {
        if (infoUI != null && stopWatch.getRuntimeAsString() != null) {
            infoUI.update(stopWatch.getRuntimeAsString(),gpGained,(int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), gpGained));
        }
    }

    public boolean isUsingMagic() {
        return usingMagic;
    }

    public void setUsingMagic(boolean usingMagic) {
        this.usingMagic = usingMagic;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getWhenToEat() {
        return whenToEat;
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

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public Boolean getGuiWait() {
        return guiWait;
    }

    public void setGuiWait(Boolean guiWait) {
        this.guiWait = guiWait;
    }

    public boolean getPriceMap(int id){
        return priceMap.containsKey(id);
    }

}