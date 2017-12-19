package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkCaveEntrance;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkMob;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class AmIInsideCave extends BranchTask {

    private WalkMob walkmob;
    private WalkCaveEntrance walkcaveentrance;
    private OpiaSlayer bot;

    public AmIInsideCave(OpiaSlayer bot){
        this.bot=bot;
        walkmob = new WalkMob(bot);
        walkcaveentrance = new WalkCaveEntrance(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMonster().getCaveArea().contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkcaveentrance;
    }

    @Override
    public TreeTask successTask() {
        return walkmob;
    }
}
