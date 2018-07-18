package com.ethan0pia.bots.CockroachKiller;

import com.ethan0pia.bots.CockroachKiller.UI.RoachUI;
import com.ethan0pia.bots.CockroachKiller.branches.Root;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

public class Roach extends TreeBot implements EmbeddableUI {

    private Player player;

    private RoachUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;
    private int mage = 0, range = 0, attack = 0;
    private int target, skill;
    private boolean go;

    private StopWatch stopWatch = new StopWatch();

    public Roach() {
        updateInfo();
        // Set this class as the EmbeddableUI
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask() {
        // Return our root tree branch
        return new Root(this);
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            // Initializing configUI in this manor is known as Lazy Instantiation
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new RoachUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onStart(String... args) {
        stopWatch.start();
        setLoopDelay(500, 800);
        new LoopingThread(() -> Platform.runLater(this::updateInfo), 1000).start();
    }

    private void updateInfo() {
        try {
            String runTime = stopWatch.getRuntimeAsString();

            if (runTime != null && infoUI != null) {
                infoUI.update(runTime,mage,range,attack);
            }
            // Assign all values to a new instance of the Info class
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMage(int mage) {
        this.mage = mage;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public boolean isGo() {
        return go;
    }

    public void setGo(boolean go) {
        this.go = go;
    }
}

