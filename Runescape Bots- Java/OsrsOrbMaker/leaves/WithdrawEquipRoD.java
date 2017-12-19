package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WithdrawEquipRoD extends LeafTask {

    private OsrsOrbMaker bot;

    public WithdrawEquipRoD(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("WithdrawEquipRoD");
        if(Bank.contains(2552)|| Inventory.contains(2552)){
            equip(2552);
        }else if(Bank.contains(2554)|| Inventory.contains(2554)){
            equip(2554);
        }else if(Bank.contains(2556)|| Inventory.contains(2556)){
            equip(2556);
        }else if(Bank.contains(2558)|| Inventory.contains(2558)){
            equip(2558);
        }else if(Bank.contains(2560)|| Inventory.contains(2560)){
            equip(2560);
        }else if(Bank.contains(2562)|| Inventory.contains(2562)){
            equip(2562);
        }else if(Bank.contains(2564)|| Inventory.contains(2564)){
            equip(2564);
        }else if(Bank.contains(2566)|| Inventory.contains(2566)){
            equip(2566);
        }else if (Bank.isOpen()){
            Environment.getLogger().severe("No Ring of Dueling left");
            bot.stop("No Ring of Dueling left");
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
