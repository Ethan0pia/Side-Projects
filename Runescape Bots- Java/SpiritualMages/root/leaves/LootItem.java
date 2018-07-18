package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class LootItem extends LeafTask {

    private OpiaSpiritualMages bot;
    private GroundItem item;
    private int fails = 0;

    public LootItem(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    public void setItem(GroundItem item) {
        this.item = item;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("LootItem");
        bot.setCurrentTask("Looting Item");
        try {
            if (!bot.getPlayer().isMoving()) {
                bot.setCountItems(true);
                if (!item.isVisible() || item.getVisibility()<50) {
                    Camera.turnTo(item, Random.nextDouble(0.6,0.9));
                } else {
                    if (item.interact("Take")) {
                        fails=0;
                        if (item.getId() == 995) {
                            bot.setGpGained(bot.getGpGained() + (item.getQuantity()));
                        }
                        Execution.delayUntil(() -> !item.isValid(), 500,2000);
                    }else if(fails>5){
                        Inventory.getItemIn(Random.nextInt(0,27)).hover();
                    }else{
                        fails++;
                    }
                }
                bot.getUtils().stuckCheck(9);
            }
        }catch(Exception e) {
            Environment.getLogger().debug("Failed to loot item");
        }
    }
}
