package com.ethan0pia.bots.OsrsOrbMaker;

import com.ethan0pia.bots.OsrsOrbMaker.branches.Root;
import com.ethan0pia.bots.OsrsOrbMaker.ui.OrbsUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.osrs.net.OSBuddyExchange;
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

public class OsrsOrbMaker extends TreeBot implements InventoryListener, EmbeddableUI {

    private int orbPrice = OSBuddyExchange.getGuidePrice(573).getOverall();
    private int orbCount;
    private boolean getGlories=false, inventoryEmptied=false, switchedWorlds=false, beenInPortal=true, passedGate = false, boughtGlories=false,
    soldGlories = false;
    private Player player;

    private OrbsUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;

    private StopWatch stopWatch = new StopWatch();

    public OsrsOrbMaker(){
        orbCount = 0;
        updateInfo();
        // Set this class as the EmbeddableUI
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask()
    {
        // Return our root tree branch
        return new Root(this);
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            // Initializing configUI in this manor is known as Lazy Instantiation
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new OrbsUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onStart(String... args){
        stopWatch.start();
        setLoopDelay(200, 400);
        new LoopingThread(() -> Platform.runLater(this::updateInfo), 1000).start();
        // Add this class as a listener for the Event Dispatcher
        getEventDispatcher().addListener(this);
        GameEvents.RS3.GRIM_REAPERS_OFFICE.disable();
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        ItemDefinition definition = event.getItem().getDefinition();
        if (definition != null && definition.getName().contains("Air orb")) {
            orbCount++;
        }
    }

    // This method is used to update the GUI thread from the bot thread
    private void updateInfo() {
        try {
            // Assign all values to a new instance of the Info class
            int rate;
            int gpPerHour;
            if(orbCount>0) {
                rate = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), orbCount);
            }else{
                rate=0;
            }
            if(orbCount>0) {
                gpPerHour = rate* orbPrice;
            }else{
                gpPerHour=0;
            }
            String runTime = stopWatch.getRuntimeAsString();

            if(runTime != null && infoUI!=null) {
                infoUI.update(runTime, rate, orbCount, gpPerHour, orbPrice*orbCount);
            }
            // Assign all values to a new instance of the Info class
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isGetGlories() {
        return getGlories;
    }
    public void setGetGlories(boolean getGlories) {
        this.getGlories = getGlories;
    }

    public boolean isInventoryEmptied() {
        return inventoryEmptied;
    }
    public void setInventoryEmptied(boolean inventoryEmptied) {
        this.inventoryEmptied = inventoryEmptied;
    }

    public boolean isSwitchedWorlds() {
        return switchedWorlds;
    }
    public void setSwitchedWorlds(boolean switchedWorlds) {
        this.switchedWorlds = switchedWorlds;
    }

    public boolean isBeenInPortal() {
        return beenInPortal;
    }
    public void setBeenInPortal(boolean beenInPortal) {
        this.beenInPortal = beenInPortal;
    }

    public boolean isPassedGate() {
        return passedGate;
    }
    public void setPassedGate(boolean passedGate) {
        this.passedGate = passedGate;
    }

    public boolean isBoughtGlories() {
        return boughtGlories;
    }
    public void setBoughtGlories(boolean boughtGlories) {
        this.boughtGlories = boughtGlories;
    }

    public boolean isSoldGlories() {
        return soldGlories;
    }
    public void setSoldGlories(boolean soldGlories) {
        this.soldGlories = soldGlories;
    }
}
