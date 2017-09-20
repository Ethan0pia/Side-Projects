package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.WithdrawSpecial;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.DepositOneFood;

/**
 * NOTES: done
 * Equip the special item.
 */
public class IsInventoryFullNeedSpecial extends BranchTask {

    private DepositOneFood depositonefood;
    private WithdrawSpecial withdrawspecial;

    public IsInventoryFullNeedSpecial(OpiaSlayer bot){
        depositonefood = new DepositOneFood(bot);
        withdrawspecial = new WithdrawSpecial(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public TreeTask failureTask() {
        return withdrawspecial;
    }

    @Override
    public TreeTask successTask() {
        return depositonefood;
    }
}
