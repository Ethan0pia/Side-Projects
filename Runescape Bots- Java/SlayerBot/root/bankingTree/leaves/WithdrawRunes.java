package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Withdraw some runes.
 */
public class WithdrawRunes extends LeafTask {

    private OpiaSlayer bot;
    private int loops;

    public WithdrawRunes(OpiaSlayer bot){
        this.bot=bot;
        loops=0;
    }

    @Override
    public void execute() {

        int spell = bot.getMonster().getSpellType();

        if(!Inventory.contains(556) || (Inventory.contains(556) && Inventory.newQuery().ids(556).results().first().getQuantity()<50) && spell!=0) {

            if(Bank.withdraw(556, 1000)){
                Execution.delayUntil(() -> Inventory.contains(556), 2000,3000);
            }
            checkLoops(556);
        }

        if (spell!=1) {
            switch (spell) {
                case 2:
                    if(!Inventory.contains(557)|| (Inventory.contains(555) && Inventory.newQuery().ids(557).results().first().getQuantity()<50)) {
                        if(Bank.withdraw(557, 1000)){
                            Execution.delayUntil(() -> Inventory.contains(557), 2000,3000);
                        }
                    }
                    checkLoops(557);
                    break;
                case 3:
                    if(!Inventory.contains(555) || (Inventory.contains(555) && Inventory.newQuery().ids(555).results().first().getQuantity()<50)) {
                        if(Bank.withdraw(555, 1000)){
                            Execution.delayUntil(() -> Inventory.contains(555), 2000,3000);
                        }
                    }
                    checkLoops(555);
                    break;
                case 4:
                    if(!Inventory.contains(554)||bot.isAlch()|| (Inventory.contains(555) && Inventory.newQuery().ids(554).results().first().getQuantity()<50)) {
                        if(Bank.withdraw(554, 1000)){
                            Execution.delayUntil(() -> Inventory.contains(554), 2000,3000);
                        }
                    }
                    checkLoops(554);
                    break;
            }
        }
        bot.getUtils().stuckCheck(12);
    }

    //stuck failsafe
    private void checkLoops(int id){
        if(Inventory.contains(id)){
            loops=0;
        }else{
            loops++;
            if(loops==5){
                Bank.close();
                loops=0;
            }
        }
    }
}