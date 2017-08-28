package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.SafeSpot;
import com.ethan0pia.bots.SlayerBot.leaves.WalkMaster;

/**
 * NOTES:
 * Checks if in combat after checking if at slayer master.
 */
public class InCombatMaster extends BranchTask {

    private GoodAssSlayerBot Bot;

    public InCombatMaster(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private SafeSpot safespot = new SafeSpot(Bot);
    private WalkMaster walkmaster = new WalkMaster(Bot);

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walkmaster;
    }

    @Override
    public TreeTask successTask() {
        return safespot;
    }
}
