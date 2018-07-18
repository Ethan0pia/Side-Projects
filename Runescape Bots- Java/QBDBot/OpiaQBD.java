package com.ethan0pia.bots.QBDBot;

import com.ethan0pia.bots.QBDBot.EatingThread.EatBotThread;
import com.ethan0pia.bots.QBDBot.EatingThread.ThreadCreator;
import com.ethan0pia.bots.QBDBot.branches.IsPlayerNull;
import com.ethan0pia.bots.QBDBot.ui.QBDUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.script.framework.listeners.ChatboxListener;
import com.runemate.game.api.script.framework.listeners.events.MessageEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import java.util.concurrent.TimeUnit;

public class OpiaQBD extends TreeBot implements ChatboxListener, EmbeddableUI {


    private boolean threadBoolean=false, bankBool=true;
    private int  stage=1,kills=0;
    private QBDUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;
    private StopWatch stopWatch = new StopWatch();
    private Player player;

    public OpiaQBD() {
        // Set this class as the EmbeddableUI
        setEmbeddableUI(this);
        stopWatch.start();
    }

    @Override
    public void onStart(String... args) {

        setLoopDelay(500, 750);
        //update timer of UI
        getEventDispatcher().addListener(this);
        new EatBotThread().start();
    }

    @Override
    public TreeTask createRootTask() {
        return new IsPlayerNull(this);
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new QBDUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onMessageReceived(MessageEvent event) {

    }

    public void update() {
        if (infoUI != null && stopWatch.getRuntimeAsString() != null) {
            infoUI.update(stopWatch.getRuntimeAsString(),kills,(int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), kills));
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public boolean isThreadBoolean() {
        return threadBoolean;
    }

    public void setThreadBoolean(boolean threadBoolean) {
        this.threadBoolean = threadBoolean;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public boolean isBankBool() {
        return bankBool;
    }

    public void setBankBool(boolean bankBool) {
        this.bankBool = bankBool;
    }
}
