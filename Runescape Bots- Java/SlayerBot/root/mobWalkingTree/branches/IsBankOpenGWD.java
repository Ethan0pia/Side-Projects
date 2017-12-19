package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.OpenBank;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WithdrawFireRunesAndLawRunes;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsBankOpenGWD extends BranchTask {

    private WithdrawFireRunesAndLawRunes withdrawfirerunesandlawrunes;
    private OpenBank openbank;
    OpiaSlayer bot;

    public IsBankOpenGWD(OpiaSlayer bot){
        this.bot=bot;
        openbank = new OpenBank(bot);
        withdrawfirerunesandlawrunes = new WithdrawFireRunesAndLawRunes(bot);
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
        return withdrawfirerunesandlawrunes;
    }
}
