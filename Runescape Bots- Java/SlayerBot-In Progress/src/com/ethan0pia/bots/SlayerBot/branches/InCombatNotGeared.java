package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.SafeSpot;
import com.ethan0pia.bots.SlayerBot.leaves.WalkBank;

/**
 * NOTES:
 * Checks if in combat if not at bank and not geared.
 */
public class InCombatNotGeared extends BranchTask {

    private GoodAssSlayerBot Bot;

    public InCombatNotGeared(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private SafeSpot safespot = new SafeSpot(Bot);
    private WalkBank walkbank = new WalkBank(Bot);

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walkbank;
    }

    @Override
    public TreeTask successTask() {
        return safespot;
    }
}
