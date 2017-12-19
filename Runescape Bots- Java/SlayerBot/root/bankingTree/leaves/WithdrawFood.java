package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Withdraw some food.
 */
public class WithdrawFood extends LeafTask {

    private OpiaSlayer bot;
    private int loops;

    public WithdrawFood(OpiaSlayer bot){
        this.bot=bot;
        loops=0;
    }

    @Override
    public void execute() {
        //searching bank for string sometimes fails the first time.
        Bank.contains(" ");
        String food = bot.getMonster().getFoodType();
        int quant = bot.getMonster().getFoodQuantity();
        if(!Bank.contains(food)){
            Environment.getLogger().severe("Correct food not in bank.");
            bot.stop();
        }
        if(!Bank.withdraw(food,quant)){
            Bank.withdraw(food,quant);
        }
        Execution.delayUntil(()-> Inventory.contains(food), 2000,3000);

        //stuck failsafe
        if(Inventory.contains(food)){
            bot.setGetFood(false);
            loops=0;
        }else{
            loops++;
            if(loops==5){
                Bank.close();
                loops=0;
            }
        }
        bot.getUtils().stuckCheck(10);
    }
}
