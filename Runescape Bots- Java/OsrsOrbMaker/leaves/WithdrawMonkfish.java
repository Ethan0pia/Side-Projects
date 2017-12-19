package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawMonkfish extends LeafTask {

    private OsrsOrbMaker bot;

    public WithdrawMonkfish(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("WithdrawMonkfish");
        if(Bank.contains(329)) {
            if(Bank.withdraw(329,1)){
                Execution.delayUntil(()-> Inventory.contains(329),2000);
            }
        }else if (Bank.isOpen()){
            Environment.getLogger().severe("Out of monkfish");
            bot.stop("Out of Monkfish");
        }
    }
}
