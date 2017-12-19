package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.DepositInventory;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.DepositOneFood;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class DoesInventoryContainFoodWalkMob extends BranchTask {

    private DepositOneFood deposit1food;
    private DepositInventory depositinventory;
    private OpiaSlayer bot;

    public DoesInventoryContainFoodWalkMob(OpiaSlayer bot){
        this.bot=bot;
        deposit1food = new DepositOneFood(bot);
        depositinventory = new DepositInventory(bot);
    }


    @Override
    public boolean validate() {
        return Inventory.contains(bot.getMonster().getFoodType());
    }

    @Override
    public TreeTask failureTask() {
        return depositinventory;
    }

    @Override
    public TreeTask successTask() {
        return deposit1food;
    }
}
