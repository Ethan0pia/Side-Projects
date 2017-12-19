package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsTaskOtherworldlyOrZygomites extends BranchTask {

    private DoIHaveDramenStaff doihavedramenstaff;
    private IsTaskInBrimhavenDungeon istaskfiregiants;
    private OpiaSlayer bot;

    public IsTaskOtherworldlyOrZygomites(OpiaSlayer bot){
        this.bot=bot;
        istaskfiregiants = new IsTaskInBrimhavenDungeon(bot);
        doihavedramenstaff = new DoIHaveDramenStaff(bot);
    }


    @Override
    public boolean validate() {
        int mob = bot.getMonster().getIndex();
        return mob==55 ||mob==74;
    }

    @Override
    public TreeTask failureTask() {
        return istaskfiregiants;
    }

    @Override
    public TreeTask successTask() {
        return doihavedramenstaff;
    }
}
