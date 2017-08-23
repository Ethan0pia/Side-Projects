package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Are there items on the ground?
 */
public class CheckGround extends BranchTask {

    private ItemWorthOverX itemworthoverx = new ItemWorthOverX();
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
        return itemworthoverx;
    }
}
