package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class CloseBank extends LeafTask {

    private OsrsOrbMaker bot;

    public CloseBank(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("CloseBank");
        if(Bank.close()){
            Execution.delayUntil(()->!Bank.isOpen(),2000);
        }
    }
}
