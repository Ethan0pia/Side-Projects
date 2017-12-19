package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Withdraw ring of kinship from bank.
 */
public class WithdrawQuickTeleport extends LeafTask {

    private OpiaSlayer bot;
    private int loops;

    public WithdrawQuickTeleport(OpiaSlayer bot){
        this.bot=bot;
        loops=0;
    }

    @Override
    public void execute() {

        if(Bank.withdraw(bot.getqTeleport(), 1)){
            Execution.delayUntil(()-> Inventory.contains(bot.getqTeleport()), 2000,3000);
        }

        //stuck failsafe
        if(Inventory.contains(bot.getqTeleport())){
            loops=0;
        }else{
            loops++;
            if(loops==5){
                Bank.close();
                loops=0;
            }
        }
        bot.getUtils().stuckCheck(11);
    }
}
