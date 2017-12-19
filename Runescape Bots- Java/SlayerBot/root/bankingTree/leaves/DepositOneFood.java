package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Deposit food to make room for special
 */
public class DepositOneFood extends LeafTask {

    private OpiaSlayer bot;

    public DepositOneFood(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Bank.deposit(bot.getMonster().getFoodType(), 1);
        Execution.delayUntil(()-> Inventory.getEmptySlots()==1,3000,5000);

        bot.getUtils().stuckCheck(4);
    }
}
