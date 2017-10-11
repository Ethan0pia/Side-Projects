package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class LootItem extends LeafTask {

    private OpiaSpiritualMages bot;
    private GroundItem item;

    public LootItem(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    public void setItem(GroundItem item) {
        this.item = item;
    }

    @Override
    public void execute() {

        if (item != null && !bot.getPlayer().isMoving()) {
            if (!item.isVisible()) {
                Camera.turnTo(item);
            } else {
                if (item.interact("Take")) {
                    if (item.getId() == 995) {
                        bot.setGpGained(bot.getGpGained() + (item.getQuantity()));
                    }
                    Execution.delayUntil(() -> !item.isValid(), 2000);
                }else{
                    Execution.delay(200, 400);
                }
            }
            bot.getUtils().stuckCheck(17);
        }
    }
}
