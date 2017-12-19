package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.EatFood;
import com.ethan0pia.bots.GargoyleBot.leaves.LootItem;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsInventoryFull extends BranchTask {

    private EatFood eattomakeroom;
    private LootItem lootitem;
    private GroundItem item;

    private GargSlayer bot;

    public IsInventoryFull(GargSlayer bot){
        this.bot=bot;
        eattomakeroom = new EatFood(bot);
        lootitem = new LootItem(bot);
    }

    public GroundItem getItem() {
        return item;
    }

    public void setItem(GroundItem item) {
        this.item = item;
    }

    @Override
    public boolean validate() {
        if(bot.getPlayer()!=null && item !=null) {
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
        }
        return true;
    }

    @Override
    public TreeTask failureTask() {
        return lootitem;
    }

    @Override
    public TreeTask successTask() {
        return eattomakeroom;
    }
}
