package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class OpenBank extends LeafTask {

    private GargSlayer bot;

    public OpenBank(GargSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Bank.open();
        Execution.delayUntil(Bank::isOpen,4000);
    }
}
