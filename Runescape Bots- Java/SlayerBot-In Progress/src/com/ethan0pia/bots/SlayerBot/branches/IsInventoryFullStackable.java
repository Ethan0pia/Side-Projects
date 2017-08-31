package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.GroundItem;
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
    private GroundItem item;

    public IsInventoryFullStackable(GoodAssSlayerBot bot, GroundItem item){
        Bot=bot;
        this.item=item;
        havestackableiteminbag = new HaveStackableItemInBag(Bot, item);
        lootitem = new LootItem(Bot, item);
    }

    private HaveStackableItemInBag havestackableiteminbag;
    private LootItem lootitem;

    @Override
    public boolean validate() {
        return Inventory.isFull();
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
