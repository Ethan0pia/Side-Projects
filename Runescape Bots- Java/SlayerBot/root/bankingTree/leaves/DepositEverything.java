package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class DepositEverything extends LeafTask {

    private OpiaSlayer bot;
    public DepositEverything(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Bank.depositInventory();
        Execution.delayUntil(Inventory::isEmpty,3000,4000);
        Bank.depositEquipment();
        Execution.delayUntil(Equipment::isEmpty,3000,4000);

        bot.getUtils().stuckCheck(2);
    }
}
