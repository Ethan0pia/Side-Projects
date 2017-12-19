package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawEquipStaff extends LeafTask {

    private OsrsOrbMaker bot;

    public WithdrawEquipStaff(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(Inventory.isFull() && Bank.isOpen()){
            if(Bank.depositInventory()){
                Execution.delayUntil(()->!Bank.isOpen(),2000);
            }
        }
        System.out.println("WithdrawEquipStaff");
        if(Inventory.contains(1381) || Bank.contains(1381) && Bank.withdraw(1381,1)){
            Execution.delayUntil(()-> Inventory.contains(1381),2000);
            if(Inventory.contains(1381) && Bank.close()){
                Execution.delayUntil(()->!Bank.isOpen(),2000);
                SpriteItem staff = Inventory.newQuery().ids(1381).results().first();
                if(!Bank.isOpen() && staff!=null){
                    if(staff.interact("Wield")){
                        Execution.delayUntil(()-> Equipment.contains(1381),2000);
                    }
                }
            }
        }else if (Bank.isOpen()){
            Environment.getLogger().severe("No staff of air");
            bot.stop("No staff of air");
        }
    }
}
