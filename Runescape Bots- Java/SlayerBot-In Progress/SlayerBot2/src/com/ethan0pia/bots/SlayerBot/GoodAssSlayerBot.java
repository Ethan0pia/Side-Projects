package com.ethan0pia.bots.SlayerBot;

import com.ethan0pia.bots.SlayerBot.branches.Root;
import com.ethan0pia.bots.SlayerBot.ui.Info;
import com.ethan0pia.bots.SlayerBot.ui.SlayerFXGUI;
import com.ethan0pia.bots.SlayerBot.ui.SlayerUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.listeners.events.ItemEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

public class GoodAssSlayerBot extends TreeBot implements InventoryListener, EmbeddableUI {

    public Info info;
	public Area mobArea;
	public Coordinate safeSpot;
	public Coordinate slayerMasterCoords;
	public String master;
	public int tasksComplete;
	public String food;

    //interfaces
    public int gpGained;
    private SlayerFXGUI configUI;
    public SlayerUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;
    public StopWatch stopWatch = new StopWatch();
    public Boolean guiWait;
    private Player player;

    public GoodAssSlayerBot(){
        // Initialize your variables
        guiWait = true;
        gpGained=0;
        // Set this class as the EmbeddableUI
        setEmbeddableUI(this);
    }

	@Override
	public void onStart(String... args) {

	    setLoopDelay(800, 1200);
        stopWatch.start();

        getEventDispatcher().addListener(this);
	}

	@Override
	public TreeTask createRootTask() {

		return new Root(this);
	}

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            // Initializing configUI in this manor is known as Lazy Instantiation
            botInterfaceProperty = new SimpleObjectProperty<>(configUI = new SlayerFXGUI(this));
            infoUI = new SlayerUI(this, master);
        }
        return botInterfaceProperty;
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        int itemID;
        if(event !=null && !Bank.isOpen()) {
            if(event.getItem().getDefinition().isNoted()) {
                itemID = event.getItem().getDefinition().getUnnotedId();
            }
            else{
                itemID = event.getItem().getId();
            }

            //check if item is tradeable in unnoted form
            if(ItemDefinition.get(itemID).isTradeable()) {
                int price = GrandExchange.lookup(itemID).getPrice();
                int amount = event.getQuantityChange();
                gpGained += (price * amount);
            }
        }
    }

    // When called, switch the botInterfaceProperty to reflect the InfoUI
    public void setToInfoProperty(){
        botInterfaceProperty.set(infoUI);
    }

}
/*
2 is goblin
4 spiders
5 birds
6 is cow
7 scorpions
9 wolves
10 zombies
13 bears
18 trolls
39 crawling hands
111 Gelatinous Abominations
*/