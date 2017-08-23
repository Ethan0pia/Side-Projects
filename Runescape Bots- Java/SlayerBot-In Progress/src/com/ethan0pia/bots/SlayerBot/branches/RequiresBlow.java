package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Does monster require a killing blow.
 */
public class RequiresBlow extends BranchTask {

    private MobHPCheck mobhpcheck = new MobHPCheck();
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
        return mobhpcheck;
    }
}
