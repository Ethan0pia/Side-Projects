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
    }

    private DepositOneFood depositonefood = new DepositOneFood(Bot);
    private WithdrawSpecialItemNotEquipable withdrawspecialitemnotequipable = new WithdrawSpecialItemNotEquipable(Bot);

    @Override
    public boolean validate() {
        if(Inventory.getEmptySlots()==0){
            return true;
        }
        else{
            return false;
        }
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
