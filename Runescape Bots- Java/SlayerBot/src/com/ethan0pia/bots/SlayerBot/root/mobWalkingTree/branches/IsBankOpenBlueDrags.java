package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.OpenBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsBankOpenBlueDrags extends BranchTask {

    private IsInventoryFullBlueDrags isinventoryfullbluedrags;
    private OpenBank openbank;

    public IsBankOpenBlueDrags(OpiaSlayer bot){
        openbank = new OpenBank(bot);
        isinventoryfullbluedrags = new IsInventoryFullBlueDrags(bot);
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
        return isinventoryfullbluedrags;
    }
}
