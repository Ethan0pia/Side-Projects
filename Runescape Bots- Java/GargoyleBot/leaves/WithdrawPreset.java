package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawPreset extends LeafTask {

    private GargSlayer bot;

    public WithdrawPreset(GargSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Bank.loadPreset(bot.getBankPreset());
        Execution.delayUntil(()->!Bank.isOpen(),3000);
        if(!Bank.isOpen()){
            bot.setBankBool(false);
        }
    }
}
