package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Does the monster require a special item to kill.
 */
public class RequiresSpecialItem extends BranchTask {

    private HasSpecialItem hasspecialitem = new HasSpecialItem();
    private AtBank atbank = new AtBank();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return atbank;
    }

    @Override
    public TreeTask successTask() {
        return hasspecialitem;
    }
}
