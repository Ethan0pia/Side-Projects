package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.LootItem;

/**
 * NOTES: done
 * checks if inventory is full if there is a stackable item on ground.
 */
public class IsInventoryFullStackable extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsInventoryFullStackable(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private HaveStackableItemInBag havestackableiteminbag = new HaveStackableItemInBag(Bot);
    private LootItem lootitem = new LootItem(Bot);

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
        return havestackableiteminbag;
    }
}
