package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.rs3.local.hud.interfaces.LootInventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Turn camera and loot item.
 */
public class LootItem extends LeafTask {

    private GoodAssSlayerBot Bot;
    private GroundItem item;

    public LootItem(GoodAssSlayerBot bot, GroundItem item){
        this.item=item;
        Bot=bot;
    }

    @Override
    public void execute() {
        if(Bot.player != null && item != null && !Bot.player.isMoving()) {
            if (!item.isVisible()) {
                Camera.turnTo(item);
                Execution.delay(2000,3000);
            } else {
                if (item.interact("Take")) {
                    Execution.delayUntil(() -> !item.isValid(), 4000);
                } else {
                    Camera.concurrentlyTurnTo(item);
                    Execution.delay(2000,3000);
                    item.interact("Take");
                    Execution.delayUntil(() -> !item.isValid(), 4000);
                }
            }
        }
    }
}
