package com.ethan0pia.bots.CockroachKiller.leaves;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.rs3.local.hud.interfaces.LootInventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class LootItems extends LeafTask {

    private Roach bot;

    public LootItems(Roach bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("13");
            if(Inventory.isFull()){
                SpriteItem food = Inventory.newQuery().actions("Eat").results().first();
                if(food!=null && food.interact("Eat")){
                    Execution.delayWhile(Inventory::isFull,100,1000);
                }
            }
            if(!Inventory.isFull()) {
                if (LootInventory.isOpen()) {
                    if (LootInventory.containsAnyOf("Rune sq shield", "Uncut diamond", "Uncut ruby", "Mithril ore", "Adamantite ore", "Off-hand rune scimitar", "Rune scimitar", "Uncut emerald")) {
                        if (LootInventory.take("Rune sq shield", "Uncut diamond", "Uncut ruby", "Mithril ore", "Adamantite ore", "Off-hand rune scimitar", "Rune scimitar", "Uncut emerald")) {
                            Execution.delayUntil(() -> LootInventory.containsAnyExcept("Rune sq shield", "Uncut diamond", "Uncut ruby", "Mithril ore", "Adamantite ore", "Off-hand rune scimitar", "Rune scimitar", "Uncut emerald"), 2000);
                            LootInventory.close();
                        }
                    } else {
                        LootInventory.close();
                    }
                } else {
                    GroundItem item = GroundItems.newQuery().names("Rune sq shield", "Uncut diamond", "Uncut ruby", "Mithril ore", "Adamantite ore", "Off-hand rune scimitar", "Rune scimitar", "Uncut emerald").results().nearest();
                    if (item != null) {
                        if (item.interact("Take")) {
                            Execution.delayUntil(bot.getPlayer()::isMoving, 200, 1000);
                            Execution.delayWhile(bot.getPlayer()::isMoving);
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
