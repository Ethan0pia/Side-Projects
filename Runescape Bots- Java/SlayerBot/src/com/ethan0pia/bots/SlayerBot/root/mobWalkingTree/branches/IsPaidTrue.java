package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.SetPaidFalse;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkMob;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsPaidTrue extends BranchTask {

    private SetPaidFalse setpaidfalse;
    private WalkMob walkmob;

    private OpiaSlayer bot;
    public IsPaidTrue(OpiaSlayer bot){
        this.bot=bot;
        setpaidfalse = new SetPaidFalse(bot);
        walkmob = new WalkMob(bot);
    }


    @Override
    public boolean validate() {
        return bot.isPaid();
    }

    @Override
    public TreeTask failureTask() {
        return walkmob;
    }

    @Override
    public TreeTask successTask() {
        return setpaidfalse;
    }
}
