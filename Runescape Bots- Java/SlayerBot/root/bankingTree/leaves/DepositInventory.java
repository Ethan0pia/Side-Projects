package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DepositInventory extends LeafTask {

    private OpiaSlayer bot;

    public DepositInventory(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(Bank.depositInventory()){
            bot.setInventoryEmptied(true);
            Execution.delayUntil(Inventory::isEmpty, 3000, 5000);
        }

        bot.getUtils().stuckCheck(3);
    }
}
