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
public class WithdrawKey extends LeafTask {

    private OpiaSlayer bot;
    private int loops;

    public WithdrawKey(OpiaSlayer bot){
        this.bot=bot;
        loops=0;
    }

    @Override
    public void execute() {
        //dusty key
        SpriteItem key = Bank.newQuery().ids(1590).results().first();
        if(key!=null) {
            Bank.withdraw(key,1);
        }else{
            Environment.getLogger().warn("Dusty key not found.");
            bot.stop();
        }
        Execution.delayUntil(()-> Inventory.contains(1590), 2000,3000);

        if(Inventory.contains(1590)){
            loops=0;
        }else{
            loops++;
            if(loops==10){
                Bank.close();
                loops=0;
            }
        }
        bot.getUtils().stuckCheck(36);
    }
}
