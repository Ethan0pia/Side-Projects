package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.ethan0pia.bots.SpiritualMages.root.leaves.LootItem;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SpiritualMages.root.leaves.EatFood;

/**
 * NOTES: done
 * Checks inventory to see if player has room for item. If they are full and have stackable item or if they have food to eat to make room.
 */
public class DoWeHaveRoomForLoot extends BranchTask {

    private EatFood eatfood;
    private LootItem lootitem;
    private OpiaSpiritualMages bot;
    private GroundItem item;

    public DoWeHaveRoomForLoot(OpiaSpiritualMages bot){
        this.bot=bot;
        eatfood = new EatFood(bot);
        lootitem = new LootItem(bot);
    }

    public void setItem(GroundItem item) {
        this.item = item;
    }

    @Override
    public boolean validate() {
        if(!Inventory.isFull()){
            lootitem.setItem(item);
            return false;
        }
        if (item.getDefinition().stacks() || item.getDefinition().isNoted()) {
            if (Inventory.contains(item.getId())) {
                lootitem.setItem(item);
                return false;
            }
        }
        return true;
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
