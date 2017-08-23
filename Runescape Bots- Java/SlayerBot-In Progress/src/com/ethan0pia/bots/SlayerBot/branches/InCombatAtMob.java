package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.AttackMob;

/**
 * NOTES:
 * 
 */
public class InCombatAtMob extends BranchTask {

    private PlayerHPCheck playerhpcheck = new PlayerHPCheck();
    private AttackMob attackmob = new AttackMob();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return attackmob;
    }

    @Override
    public TreeTask successTask() {
        return playerhpcheck;
    }
}
