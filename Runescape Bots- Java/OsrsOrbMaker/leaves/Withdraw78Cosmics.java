package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class Withdraw78Cosmics extends LeafTask {

    private OsrsOrbMaker bot;

    public Withdraw78Cosmics(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("Withdraw78Cosmics");
        if(Bank.withdraw(564,78)){
            Execution.delayUntil(()-> Inventory.contains(564),2000);
        }
    }
}
