package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if any item on the ground is worth over Xgp.
 */
public class ItemWorthOverX extends BranchTask {

    private IsInventoryFullLooting isinventoryfulllooting = new IsInventoryFullLooting();
    private IsStackable isstackable = new IsStackable();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isstackable;
    }

    @Override
    public TreeTask successTask() {
        return isinventoryfulllooting;
    }
}
