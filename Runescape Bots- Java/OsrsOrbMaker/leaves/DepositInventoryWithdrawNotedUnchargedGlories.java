package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import static com.runemate.game.api.hybrid.local.hud.interfaces.Bank.WithdrawMode.NOTE;

/**
 * NOTES:
 * 
 */
public class DepositInventoryWithdrawNotedUnchargedGlories extends LeafTask {

    private OsrsOrbMaker bot;

    public DepositInventoryWithdrawNotedUnchargedGlories(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("DepositInventoryWithdrawNotedUnchargedGlories");
        if(Bank.depositInventory()){
            Execution.delayUntil(Inventory::isEmpty,2000);
            if(Inventory.isEmpty()){
                if(Bank.setWithdrawMode(NOTE)){
                    Execution.delay(1000,2000);
                    if(Bank.withdraw(1704,200)){
                        Execution.delayUntil(()->Inventory.contains(1705),2000);
                    }
                }
            }
        }
    }
}
