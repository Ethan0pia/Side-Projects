package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Withdraw the special item
 */
public class WithdrawSpecial extends LeafTask {

    private OpiaSlayer bot;
    private int loops;

    public WithdrawSpecial(OpiaSlayer bot){
        this.bot=bot;
        loops=0;
    }

    @Override
    public void execute() {
        int special = bot.getMonster().getSpecialItem();
        if(bot.getUtils().alternativeSpecialsInBank()!=0){
            special = bot.getUtils().alternativeSpecialsInBank();
        }
        int item = special;
        if(item==1823){
            if(Bank.withdraw(item, 3)){
                Execution.delayUntil(()-> Inventory.contains(item), 2000,3000);
            }
        }else {
            if(Bank.withdraw(item, 1)){
                Execution.delayUntil(()-> Inventory.contains(item), 2000,3000);
            }
        }

        //stuck failsafe
        if(Inventory.contains(item)){
            loops=0;
        }else{
            loops++;
            if(loops==5){
                Bank.close();
                loops=0;
            }
        }
        bot.getUtils().stuckCheck(13);
    }
}
