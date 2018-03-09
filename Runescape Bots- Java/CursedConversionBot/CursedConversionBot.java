package com.ethan0pia.bots.CursedConversionBot;

import com.ethan0pia.bots.CursedConversionBot.branches.Root;
import com.ethan0pia.bots.CursedConversionBot.ui.CursedConversionUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
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

public class CursedConversionBot extends TreeBot implements InventoryListener, EmbeddableUI {

    private int itemCount=0;
    private boolean guiWait = true;
    private String energy = null;
    private Player player;

    private CursedConversionUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;

    private StopWatch stopWatch = new StopWatch();

    public CursedConversionBot(){
        itemCount = 0;
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
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new CursedConversionUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onStart(String... args){
        stopWatch.start();
        setLoopDelay(300, 800);
        new LoopingThread(() -> Platform.runLater(this::updateInfo), 200).start();
        // Add this class as a listener for the Event Dispatcher
        getEventDispatcher().addListener(this);
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        if(!Bank.isOpen()) {
            ItemDefinition definition = event.getItem().getDefinition();
            if (definition != null && energy !=null && definition.getName().equals(energy)) {
                itemCount = itemCount + 150;
            }
        }
    }

    // This method is used to update the GUI thread from the bot thread
    private void updateInfo() {
        try {
            // Assign all values to a new instance of the Info class
            int rate = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), itemCount);
            String runTime = stopWatch.getRuntimeAsString();

            if(runTime != null && infoUI!=null) {
                infoUI.update(rate, itemCount, runTime);
            }
            // Assign all values to a new instance of the Info class
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isGuiWait() {
        return guiWait;
    }

    public void setGuiWait(boolean toSet){
        guiWait=toSet;
    }

    public String getEnergy(){
        return energy;
    }

    public void setEnergy(String toSet){
        energy=toSet;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
