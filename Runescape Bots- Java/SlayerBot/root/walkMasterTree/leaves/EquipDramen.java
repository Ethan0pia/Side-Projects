package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
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
public class EquipDramen extends LeafTask {

    private OpiaSlayer bot;
    private int loops;

    public EquipDramen(OpiaSlayer bot){
        this.bot=bot;
        loops=0;
    }

    @Override
    public void execute() {
        SpriteItem staff = Inventory.newQuery().ids(772).results().first();
        if(staff!=null){
            if(Inventory.equip(staff)){
                Execution.delayUntil(()-> Equipment.contains(772), 1000,3000);
            }
        }else if(Bank.isOpen()){
            staff = Bank.newQuery().ids(772).results().first();
            if(staff!=null){
                if(Inventory.equip(staff)){
                    Execution.delayUntil(()-> Equipment.contains(772), 1000,3000);
                }
            }
        }

        if(Equipment.contains(772)){
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
        bot.getUtils().stuckCheck(38);
    }
}
