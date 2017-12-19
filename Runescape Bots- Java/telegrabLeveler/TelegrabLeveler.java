package com.ethan0pia.bots.telegrabLeveler;

import com.ethan0pia.bots.telegrabLeveler.branches.Root;
import com.ethan0pia.bots.telegrabLeveler.ui.TelegrabberUI;
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

public class TelegrabLeveler extends TreeBot implements EmbeddableUI {

    private int beginningMagicLvl = Skill.MAGIC.getBaseLevel(), currentMagicLevel = Skill.MAGIC.getBaseLevel();
    private Player player;

    private TelegrabberUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;

    private StopWatch stopWatch = new StopWatch();

    public TelegrabLeveler() {
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
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new TelegrabberUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onStart(String... args) {
        stopWatch.start();
        setLoopDelay(300, 450);
        new LoopingThread(() -> Platform.runLater(() -> updateInfo()), 1000).start();
    }

    private void updateInfo() {
        try {
            String runTime = stopWatch.getRuntimeAsString();
            int levelsGained = 0;

            if(beginningMagicLvl==0){
                beginningMagicLvl=currentMagicLevel;
            }else{
                levelsGained=currentMagicLevel-beginningMagicLvl;
            }

            if (runTime != null && infoUI != null) {
                infoUI.update(runTime, levelsGained, currentMagicLevel);
            }
            // Assign all values to a new instance of the Info class
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCurrentMagicLevel(int currentMagicLevel) {
        this.currentMagicLevel = currentMagicLevel;
    }
}

