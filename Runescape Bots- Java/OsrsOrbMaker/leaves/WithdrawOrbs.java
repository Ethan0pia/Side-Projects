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
public class WithdrawOrbs extends LeafTask {

    private OsrsOrbMaker bot;

    public WithdrawOrbs(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("WithdrawOrbs");
        if(Bank.contains(567)){
            if(Bank.withdraw(567,78)){
                Execution.delayUntil(()-> Inventory.contains(567),2000);
            }
        }else{
            Environment.getLogger().severe("Out of orbs");
        }
    }
}
