package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class DoesMobRequireBasicSpecial extends BranchTask {

    private AmIAtCaveEntrance amiatcaveentrance;
    private DoesMobRequireCustomPath doesmobrequirecustompath;
    private OpiaSlayer bot;

    public DoesMobRequireBasicSpecial(OpiaSlayer bot){
        this.bot=bot;
        doesmobrequirecustompath = new DoesMobRequireCustomPath(bot);
        amiatcaveentrance = new AmIAtCaveEntrance(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMonster().isBasicSpecialPath();
    }

    @Override
    public TreeTask failureTask() {
        return doesmobrequirecustompath;
    }

    @Override
    public TreeTask successTask() {
        return amiatcaveentrance;
    }
}
