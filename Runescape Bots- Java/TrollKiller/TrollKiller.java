package com.ethan0pia.bots.TrollKiller;

import com.ethan0pia.bots.TrollKiller.branches.Root;
import com.ethan0pia.bots.TrollKiller.ui.TrollKillerUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

public class TrollKiller extends TreeBot implements EmbeddableUI {

    private Player player;

    private TrollKillerUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;
    private int mage = 0, range = 0, attack = 0;
    private int magicTarget, rangedTarget, attackTarget;
    private boolean go;

    private StopWatch stopWatch = new StopWatch();

    public TrollKiller() {
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
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new TrollKillerUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onStart(String... args) {
        stopWatch.start();
        setLoopDelay(300, 450);
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

    public int getMagicTarget() {
        return magicTarget;
    }

    public void setMagicTarget(int magicTarget) {
        this.magicTarget = magicTarget;
    }

    public int getRangedTarget() {
        return rangedTarget;
    }

    public void setRangedTarget(int rangedTarget) {
        this.rangedTarget = rangedTarget;
    }

    public int getAttackTarget() {
        return attackTarget;
    }

    public void setAttackTarget(int attackTarget) {
        this.attackTarget = attackTarget;
    }

    public boolean isGo() {
        return go;
    }

    public void setGo(boolean go) {
        this.go = go;
    }
}

