package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.LootItem;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.leaves.EatFood;

/**
 * NOTES: done
 * Checks inventory to see if player has room for item. If they are full and have stackable item or if they have food to eat to make room.
 */
public class IsInventoryFullOrContainsStackableAlready extends BranchTask {

    private EatFood eatfood;
    private LootItem lootitem;
    private OpiaSpiritualMages bot;
    private GroundItem item;

    public IsInventoryFullOrContainsStackableAlready(OpiaSpiritualMages bot){
        this.bot=bot;
        eatfood = new EatFood(bot);
        lootitem = new LootItem(bot);
    }

    public void setItem(GroundItem item) {
        this.item = item;
    }

    @Override
    public boolean validate() {
        return bot.getUtils().inventoryHaveRoom(item,lootitem);
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
