package com.ethan0pia.bots.WineGrabber;

import com.ethan0pia.bots.WineGrabber.branches.IsPlayerNull;
import com.ethan0pia.bots.WineGrabber.ui.WineInfoUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.net.GrandExchange;
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

import java.util.concurrent.TimeUnit;

public class OpiaWineGrabber extends TreeBot implements InventoryListener, EmbeddableUI {

    private int winePrice = GrandExchange.lookup(245).getPrice();
    private int wineCount, leafId=0, wineLost = 0;
    private boolean run=false, bankBool=false;
    private Player player;
    private Coordinate telegrabSpot=new Coordinate(2952,3474,0);

    private WineInfoUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;

    private StopWatch stopWatch = new StopWatch();

    public OpiaWineGrabber(){
        wineCount = 0;
        updateInfo();
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask()
    {
        return new IsPlayerNull(this);
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new WineInfoUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onStart(String... args){
        stopWatch.start();
        setLoopDelay(300, 500);
        new LoopingThread(() -> Platform.runLater(this::updateInfo), 1000).start();
        getEventDispatcher().addListener(this);
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        ItemDefinition definition = event.getItem().getDefinition();
        if (definition != null && definition.getName().contains("Wine of Zamorak")) {
            wineCount++;
        }
    }

    // This method is used to update the GUI thread from the bot thread
    private void updateInfo() {
        try {
            // Assign all values to a new instance of the Info class
            int rate;
            int lossRate;
            if(wineLost>0){
                lossRate = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), wineLost);
            }else{
                lossRate=0;
            }
            if(wineCount>0) {
                rate = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), wineCount);
            }else{
                rate=0;
            }
            String runTime = stopWatch.getRuntimeAsString();

            if(runTime != null && infoUI!=null) {
                infoUI.update(rate, wineCount, runTime, winePrice, wineLost, lossRate);
            }
            // Assign all values to a new instance of the Info class
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int getLeafId() {
        return leafId;
    }
    public void setLeafId(int leafId) {
        this.leafId = leafId;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isRun() {
        return run;
    }
    public void setRun(boolean run){
        this.run=run;
    }

    public Coordinate getTelegrabSpot() {
        return telegrabSpot;
    }

    public boolean isBankBool() {
        return bankBool;
    }
    public void setBankBool(boolean bankBool) {
        this.bankBool = bankBool;
    }

    public int getWineLost() {
        return wineLost;
    }
    public void setWineLost(int wineLost) {
        this.wineLost = wineLost;
    }
}
