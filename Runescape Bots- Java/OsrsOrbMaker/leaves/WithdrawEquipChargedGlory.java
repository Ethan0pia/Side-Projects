package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
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
public class WithdrawEquipChargedGlory extends LeafTask {

    private OsrsOrbMaker bot;

    public WithdrawEquipChargedGlory(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("WithdrawEquipRoD");
        if(Bank.contains(1706)|| Inventory.contains(1706)){
            equip(1706);
        }else if(Bank.contains(1708)|| Inventory.contains(1708)){
            equip(1708);
        }else if(Bank.contains(1710)|| Inventory.contains(1710)){
            equip(1710);
        }else if(Bank.contains(1712)|| Inventory.contains(1712)){
            equip(1712);
        }else if(Bank.contains(11976)|| Inventory.contains(11976)){
            equip(11976);
        }else if(Bank.contains(11978)|| Inventory.contains(11978)){
            equip(11978);
        }else{
            bot.setGetGlories(true);
        }
    }

    private void equip(int id){
        if(Inventory.isFull() && Bank.isOpen()){
            if(Bank.depositInventory()){
                Execution.delayUntil(()->!Bank.isOpen(),2000);
            }
        }
        if(Inventory.contains(id) || Bank.contains(id) && Bank.withdraw(id,1)) {
            Execution.delayUntil(() -> Inventory.contains(id), 2000);
            if (Inventory.contains(id) && Bank.close()) {
                Execution.delayUntil(() -> !Bank.isOpen(), 2000);
                SpriteItem ring = Inventory.newQuery().ids(id).results().first();
                if (!Bank.isOpen() && ring != null) {
                    if (ring.interact("Wear")) {
                        Execution.delayUntil(() -> Equipment.contains(id), 2000);
                    }
                }
            }
        }
    }
}
