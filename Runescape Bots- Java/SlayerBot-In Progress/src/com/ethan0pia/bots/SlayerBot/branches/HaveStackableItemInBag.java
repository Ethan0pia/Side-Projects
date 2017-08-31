package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.LootItem;

/**
 * NOTES: done
 * Checks bag for same stackable.
 */
public class HaveStackableItemInBag extends BranchTask {

    private GoodAssSlayerBot Bot;
    private GroundItem item;

    public HaveStackableItemInBag(GoodAssSlayerBot bot, GroundItem item){
        Bot=bot;
        this.item=item;
        lootitem = new LootItem(Bot, item);
        incombatatmob = new InCombatAtMob(Bot);
    }

    private LootItem lootitem;
    private InCombatAtMob incombatatmob;

    @Override
    public boolean validate() {
        if(Bot.player!=null && item !=null){
            if(Inventory.contains(item.getId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return incombatatmob;
    }

    @Override
    public TreeTask successTask() {
        return lootitem;
    }
}
