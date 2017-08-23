package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.EatFood;
import com.ethan0pia.bots.SlayerBot.leaves.LootItem;

/**
 * NOTES: done
 * Checks if bag is full before looting.
 */
public class IsInventoryFullLooting extends BranchTask {

    private EatFood eatfood = new EatFood();
    private LootItem lootitem = new LootItem();

    @Override
    public boolean validate() {
        if(Inventory.getEmptySlots()==0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public TreeTask failureTask() {
        return lootitem;
    }

    @Override
    public TreeTask successTask() {
        return eatfood;
    }
}
