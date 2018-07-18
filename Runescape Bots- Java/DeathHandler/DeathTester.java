package com.ethan0pia.bots.DeathHandler;

import com.ethan0pia.bots.TanBot.branches.IsBankOpen;
import com.ethan0pia.bots.TanBot.ui.PortableInfoUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.GameEvents;
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

public class DeathTester extends TreeBot {

    @Override
    public TreeTask createRootTask()
    {
        // Return our root tree branch
        return new Root();
    }

    @Override
    public void onStart(String... args){
        GameEvents.RS3.GRIM_REAPERS_OFFICE.disable();
        setLoopDelay(200, 400);
    }
}
