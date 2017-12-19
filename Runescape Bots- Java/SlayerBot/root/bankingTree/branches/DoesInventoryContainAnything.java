package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.DepositEverything;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.SetBankBooleanTrue;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks if inventory is empty after banking for new task.
 */
public class DoesInventoryContainAnything extends BranchTask {

    private DepositEverything depositeverything;
    private SetBankBooleanTrue setbankbooleantrue;

    public DoesInventoryContainAnything(OpiaSlayer bot){
        depositeverything = new DepositEverything(bot);
        setbankbooleantrue = new SetBankBooleanTrue(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return depositeverything;
    }

    @Override
    public TreeTask successTask() {
        return setbankbooleantrue;
    }
}
