package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.DepositOneFood;
import com.ethan0pia.bots.SlayerBot.leaves.WithdrawSpecialItemNotEquipable;

/**
 * NOTES: done
 * Checks if inventory is full when we need to withdraw a special item from bank.
 */
public class IsInventoryFullSpecialItemNotEquipable extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsInventoryFullSpecialItemNotEquipable(GoodAssSlayerBot bot){
        Bot=bot;
        depositonefood = new DepositOneFood(Bot);
        withdrawspecialitemnotequipable = new WithdrawSpecialItemNotEquipable(Bot);
    }

    private DepositOneFood depositonefood;
    private WithdrawSpecialItemNotEquipable withdrawspecialitemnotequipable;

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public TreeTask failureTask() {
        return withdrawspecialitemnotequipable;
    }

    @Override
    public TreeTask successTask() {
        return depositonefood;
    }
}
