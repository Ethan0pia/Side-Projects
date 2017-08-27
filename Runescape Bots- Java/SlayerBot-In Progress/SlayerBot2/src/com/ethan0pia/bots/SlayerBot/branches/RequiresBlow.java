package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.leaves.EmptyLeaf;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Does monster require a killing blow.
 */
public class RequiresBlow extends BranchTask {

    private MobHPCheck mobhpcheck = new MobHPCheck();
	private EmptyLeaf empty = new EmptyLeaf();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return empty;
    }

    @Override
    public TreeTask successTask() {
        return mobhpcheck;
    }
}
