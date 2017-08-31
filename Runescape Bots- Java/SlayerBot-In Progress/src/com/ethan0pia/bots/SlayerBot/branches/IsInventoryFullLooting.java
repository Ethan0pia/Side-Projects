package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.GroundItem;
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

    private GoodAssSlayerBot Bot;
    private GroundItem item;

    public IsInventoryFullLooting(GoodAssSlayerBot bot, GroundItem item){
        Bot=bot;
        this.item=item;
        eatfood = new EatFood(Bot);
        lootitem = new LootItem(Bot, item);
    }

    private EatFood eatfood;
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
        return eatfood;
    }
}
