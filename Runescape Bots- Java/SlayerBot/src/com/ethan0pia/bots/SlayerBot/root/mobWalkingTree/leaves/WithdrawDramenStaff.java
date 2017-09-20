package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawDramenStaff extends LeafTask {

    private OpiaSlayer bot;
    private int loops;

    public WithdrawDramenStaff(OpiaSlayer bot){
        this.bot=bot;
        loops=0;
    }

    @Override
    public void execute() {
        SpriteItem staff = Bank.newQuery().ids(772).results().first();
        if(staff!=null) {
            Bank.withdraw(staff,1);
        }else{
            Environment.getLogger().warn("Dramen staff not found.");
            bot.stop();
        }
        Execution.delayUntil(()-> Inventory.contains(772), 2000,3000);

        if(Inventory.contains(772)){
            loops=0;
            if(Bank.isOpen()) {
                Bank.close();
            }
        }else{
            loops++;
            if(loops==10){
                Bank.close();
                loops=0;
            }
        }
        bot.getUtils().stuckCheck("ah");
    }
}
