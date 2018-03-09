package com.ethan0pia.bots.CowKiller;

import com.ethan0pia.bots.CowKiller.UI.CowInfoUI;
import com.ethan0pia.bots.CowKiller.branches.InventoryFull;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
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

public class CowKiller extends TreeBot implements InventoryListener, EmbeddableUI {

    private int leatherPrice = GrandExchange.lookup(1741).getPrice();
    private int hideCount=0;

    private CowInfoUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;

    private StopWatch stopWatch = new StopWatch();

    public CowKiller(){
        hideCount = 0;
        updateInfo();
        // Set this class as the EmbeddableUI
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask()
    {
        // Return our root tree branch
        return new InventoryFull();
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            // Initializing configUI in this manor is known as Lazy Instantiation
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new CowInfoUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onStart(String... args){
        stopWatch.start();
        setLoopDelay(1000, 2000);
        new LoopingThread(() -> Platform.runLater(() -> updateInfo()), 1000).start();
        // Add this class as a listener for the Event Dispatcher
        getEventDispatcher().addListener(this);
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        ItemDefinition definition = event.getItem().getDefinition();
        if (definition != null && definition.getName().contains("Cowhide")) {
            hideCount++;
        }
    }

    // This method is used to update the GUI thread from the bot thread
    private void updateInfo() {
        try {
            // Assign all values to a new instance of the Info class
            int rate = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), hideCount);
            int gpRate = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), hideCount*leatherPrice);
            String runTime = stopWatch.getRuntimeAsString();

            if(runTime != null && infoUI!=null) {
                infoUI.update(rate, hideCount, runTime, leatherPrice, gpRate);
            }
            // Assign all values to a new instance of the Info class
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}