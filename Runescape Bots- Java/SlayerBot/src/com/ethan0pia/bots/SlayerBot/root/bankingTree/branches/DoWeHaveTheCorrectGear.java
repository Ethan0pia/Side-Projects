package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.EquipGear;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoWeHaveTheCorrectGear extends BranchTask {

    private DoesInventoryContainFood doesInventoryContainFood;
    private EquipGear equipGear;
    private OpiaSlayer bot;

    public DoWeHaveTheCorrectGear(OpiaSlayer bot){
        this.bot=bot;
        equipGear = new EquipGear(bot);
        doesInventoryContainFood = new DoesInventoryContainFood(bot);
    }

    @Override
    public boolean validate() {
        String gear[]=bot.getMonster().getGear();
        for(int i=0;i<12;i++){
            if(gear[i]!=null){
                boolean bankContains = Bank.contains(gear[i]);
                boolean inventoryContains = Inventory.contains(gear[i]);
                if((bankContains || inventoryContains) && !Equipment.contains(gear[i])){
                    SpriteItem item;
                    if(bankContains && !inventoryContains) {
                        item = Bank.newQuery().names(gear[i]).results().first();
                    }else{
                        item = Inventory.newQuery().names(gear[i]).results().first();
                    }
                    if(item!=null){
                        equipGear.setItem(item);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public TreeTask failureTask() {
        return equipGear;
    }

    @Override
    public TreeTask successTask() {
        return doesInventoryContainFood;
    }
}
