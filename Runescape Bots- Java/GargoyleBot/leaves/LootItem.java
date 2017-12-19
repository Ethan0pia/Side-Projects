package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class LootItem extends LeafTask {

    private GargSlayer bot;
    private GroundItem item;

    public LootItem(GargSlayer bot){
        this.bot=bot;
    }

    public void setItem(GroundItem item) {
        this.item = item;
    }

    @Override
    public void execute() {
        if(bot.getPlayer() != null && item != null && item.getDefinition()!=null && !bot.getPlayer().isMoving()) {
            if (!item.isVisible()) {
                Camera.turnTo(item);
            } else {
                if (!item.interact("Take")) {
                    Camera.concurrentlyTurnTo(item);
                    Execution.delay(200,400);
                    item.click();
                }
                Execution.delayUntil(() -> !item.isValid(), 1000,2000);
            }
        }
    }
}
