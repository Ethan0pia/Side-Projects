package com.ethan0pia.bots.TanBot.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawPreset extends LeafTask {

    @Override
    public void execute() {
        if(Bank.loadPreset(1,true)){
            Execution.delayUntil(()->!Bank.isOpen(),600);
            if(!Bank.isOpen() && !Inventory.isFull()){
                Environment.getBot().stop("out of materials");
            }
        }
    }
}
