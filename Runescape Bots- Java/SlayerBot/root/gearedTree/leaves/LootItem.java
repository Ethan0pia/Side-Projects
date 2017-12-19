package com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.rs3.local.hud.interfaces.MoneyPouch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class LootItem extends LeafTask {

    private OpiaSlayer bot;
    private GroundItem item;
    private int gpBefore;

    public LootItem(OpiaSlayer bot){
        this.bot=bot;
    }

    public void setItem(GroundItem item) {
        this.item = item;
    }

    @Override
    public void execute() {

        if(item != null && item.getDefinition()!=null && !bot.getPlayer().isMoving()) {
            if (!item.isVisible()) {
                Camera.turnTo(item);
            } else {
                if(item.getId()==995){
                    gpBefore= MoneyPouch.getContents();
                }
                item.hover();
                Execution.delay(200,500);
                if (item.interact("Take")) {
                    if(item.getId()==995){
                        bot.setGpGained(bot.getGpGained()+(MoneyPouch.getContents()-gpBefore));
                    }
                    Execution.delayUntil(() -> !item.isValid(), 2000);
                }
            }
        }
        bot.getUtils().stuckCheck(17);
    }
}
