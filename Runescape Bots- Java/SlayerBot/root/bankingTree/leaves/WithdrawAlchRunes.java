package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WithdrawAlchRunes extends LeafTask {

    private OpiaSlayer bot;
    private int loops;

    public WithdrawAlchRunes(OpiaSlayer bot){
        this.bot=bot;
        loops=0;
    }

    @Override
    public void execute() {
        //fire runes
        if(!Inventory.contains(554)) {
            if(Bank.withdraw(554, 500)){
                Execution.delayUntil(() -> Inventory.contains(554), 2000,3000);
            }
        }
        //nature runes
        if(!Inventory.contains(561)){
            if(Bank.withdraw(561, 100)){
                Execution.delayUntil(() -> Inventory.contains(561), 2000,3000);
            }
        }

        //stuck failsafe
        if(Inventory.contains(561)){
            loops=0;
        }else{
            loops++;
            if(loops==5){
                Bank.close();
                loops=0;
            }
        }

        bot.getUtils().stuckCheck(9);
    }
}
