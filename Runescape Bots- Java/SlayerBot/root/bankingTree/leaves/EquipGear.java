package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EquipGear extends LeafTask {

    private OpiaSlayer bot;
    private SpriteItem item;

    public EquipGear(OpiaSlayer bot){
        this.bot=bot;
    }

    public void setItem(SpriteItem item) {
        this.item = item;
    }

    @Override
    public void execute() {
        if(item!=null) {
            if(Bank.contains(item.getId())&&!Inventory.contains(item.getId())) {
                //only need 400 arrows/bolts for a task.
                if(item.getDefinition().getEquipmentSlot()==Equipment.Slot.AMMUNITION){
                    Bank.withdraw(item,400);
                    Execution.delayUntil(()->Inventory.contains(item.getId()),2000,3000);
                }else{
                    Bank.equip(item);
                }
            }else{
                Inventory.equip(item);
                Execution.delayUntil(()-> Equipment.contains(item.getId()),2000,3500);
            }
        }

        bot.getUtils().stuckCheck(5);
    }
}
