package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.SafeSpot;
import com.ethan0pia.bots.SlayerBot.leaves.WalkMob;

/**
 * NOTES:
 * Checks if in combat while not at the mobs, but geared.
 */
public class InCombatNotAtMob extends BranchTask {

    private SafeSpot safespot = new SafeSpot();
    private WalkMob walkmob = new WalkMob();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walkmob;
    }

    @Override
    public TreeTask successTask() {
        return safespot;
    }
}
