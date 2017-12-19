package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawFireRunesAndLawRunes extends LeafTask {

    private OpiaSlayer bot;

    public WithdrawFireRunesAndLawRunes(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(Bank.contains(563)&&Bank.contains(554)) {
            if (Bank.withdraw(554, 2)) {
                Execution.delayUntil(() -> Inventory.contains(554), 2000, 3000);
            }
            if (Bank.withdraw(563, 2)) {
                Execution.delayUntil(() -> Inventory.contains(563), 2000, 3000);
            }
        }else{
            Environment.getLogger().severe("No Law Runes or Fire runes in bank.");
            bot.stop();
        }
        bot.getUtils().stuckCheck(49);
    }
}
