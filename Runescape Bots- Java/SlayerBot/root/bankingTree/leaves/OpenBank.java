package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class OpenBank extends LeafTask {

    private OpiaSlayer bot;

    public OpenBank(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(Bank.open()){
            Execution.delayUntil(Bank::isOpen,3000,4000);
        }

        bot.getUtils().stuckCheck(6);
    }
}
