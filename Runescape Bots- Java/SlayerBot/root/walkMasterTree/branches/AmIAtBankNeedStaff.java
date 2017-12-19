package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.WalkBank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIAtBankNeedStaff extends BranchTask {

    private IsBankOpenNeedStaff isbankopenneedstaff;
    private WalkBank walkbank;
    private OpiaSlayer bot;

    public AmIAtBankNeedStaff(OpiaSlayer bot){
        this.bot=bot;
        walkbank = new WalkBank(bot);
        isbankopenneedstaff = new IsBankOpenNeedStaff(bot);
    }

    @Override
    public boolean validate() {
        return bot.getUtils().amIatBank();
    }

    @Override
    public TreeTask failureTask() {
        return walkbank;
    }

    @Override
    public TreeTask successTask() {
        return isbankopenneedstaff;
    }
}
