package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Am I at the monster's location.
 */
public class AtMonster extends BranchTask {
    private CheckGround checkground = new CheckGround();
    private InCombatNotAtMob incombatnotatmob = new InCombatNotAtMob();

    @Override
    public boolean validate() {

        return false;
    }

    @Override
    public TreeTask failureTask() {
        return incombatnotatmob;
    }

    @Override
    public TreeTask successTask() {
        return checkground;
    }
}
