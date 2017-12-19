package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.OpenBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * 
 */
public class IsBankOpenNotGeared extends BranchTask {

    private IsBankBoolTrue isBankBoolTrue;
    private OpenBank openbank;

    public IsBankOpenNotGeared(OpiaSlayer bot){
        isBankBoolTrue = new IsBankBoolTrue(bot);
        openbank = new OpenBank(bot);
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return openbank;
    }

    @Override
    public TreeTask successTask() {
        return isBankBoolTrue;
    }
}
