package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkMob;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class DoesMobRequireCustomPath extends BranchTask {

    private IsTaskAnkous istaskankous;
    private WalkMob walkmob;
    private OpiaSlayer bot;

    public DoesMobRequireCustomPath(OpiaSlayer bot){
        this.bot=bot;
        walkmob = new WalkMob(bot);
        istaskankous = new IsTaskAnkous(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMonster().isAdvancedSpecialPath();
    }

    @Override
    public TreeTask failureTask() {
        return walkmob;
    }

    @Override
    public TreeTask successTask() {
        return istaskankous;
    }
}
