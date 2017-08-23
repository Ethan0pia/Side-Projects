package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.KillingBlow;

/**
 * NOTES:
 * Is monster's HP below 10%?
 */
public class MobHPCheck extends BranchTask {

    private KillingBlow killingblow = new KillingBlow();
	private HaveTask haveTask = new HaveTask();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return haveTask;
    }

    @Override
    public TreeTask successTask() {
        return killingblow;
    }
}
