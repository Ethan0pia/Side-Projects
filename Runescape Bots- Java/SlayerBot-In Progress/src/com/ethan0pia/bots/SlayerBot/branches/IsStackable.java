package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if any item on the ground is stackable.
 */
public class IsStackable extends BranchTask {

    private IsInventoryFullStackable isinventoryfullstackable = new IsInventoryFullStackable();
    private InCombatAtMob incombatatmob = new InCombatAtMob();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return incombatatmob;
    }

    @Override
    public TreeTask successTask() {
        return isinventoryfullstackable;
    }
}
