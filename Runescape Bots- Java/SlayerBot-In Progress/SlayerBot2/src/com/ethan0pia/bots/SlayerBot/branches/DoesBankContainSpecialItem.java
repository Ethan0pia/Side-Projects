package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.StopBot;

/**
 * NOTES:
 * Checks bank for special item
 */
public class DoesBankContainSpecialItem extends BranchTask {

    private IsSpecialEquipable isspecialequipable = new IsSpecialEquipable();
    private StopBot stopbot = new StopBot();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return stopbot;
    }

    @Override
    public TreeTask successTask() {
        return isspecialequipable;
    }
}
