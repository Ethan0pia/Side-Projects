package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.WithdrawFood;

/**
 * NOTES: done
 * DoWeHaveFood
 */
public class DoesInventoryContainFood extends BranchTask {

    private DoINeedSpecialItem doINeedSpecialItem;
    private WithdrawFood withdrawfood;
    private OpiaSlayer bot;

    public DoesInventoryContainFood(OpiaSlayer bot){
        this.bot=bot;
        doINeedSpecialItem = new DoINeedSpecialItem(bot);
        withdrawfood = new WithdrawFood(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.contains(bot.getMonster().getFoodType());
    }


    @Override
    public TreeTask failureTask() {
        return withdrawfood;
    }

    @Override
    public TreeTask successTask() {
        return doINeedSpecialItem;
    }
}
