package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if special item needed is equipped or in bag.
 */
public class HasSpecialItem extends BranchTask {

    private AtBankToWithdrawSpecial atbanktowithdrawspecial = new AtBankToWithdrawSpecial();
    private AtBank atbank = new AtBank();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return atbanktowithdrawspecial;
    }

    @Override
    public TreeTask successTask() {
        return atbank;
    }
}
