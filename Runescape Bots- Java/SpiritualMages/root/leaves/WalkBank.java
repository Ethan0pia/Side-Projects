package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class WalkBank extends LeafTask {
    private OpiaSpiritualMages bot;
    private Area bank=new Area.Circular(new Coordinate(2725,3492,0),8);
    private Area camelot = new Area.Circular(new Coordinate(2737,3483,0), 80);

    public WalkBank(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("WalkFallyBank");
        bot.setCurrentTask("Walking to Bank");
        try {
            if (!camelot.contains(bot.getPlayer())) {
                SpriteItem tablet = Inventory.getItems(8010).first();
                if (tablet != null) {
                    if (tablet.interact("Break")) {
                        Execution.delayUntil(() -> camelot.contains(bot.getPlayer()) || !Inventory.contains(bot.getTeleport()), 1000);
                    }
                }
            }
            if (Camera.getPitch() < 0.5) {
                Camera.concurrentlyTurnTo(Random.nextDouble(0.5,0.7));
            }
            if (!bank.contains(bot.getPlayer())) {
                bot.getUtils().walkPath(bank.getCenter());
            }
            bot.getUtils().stuckCheck(14);
        }catch(Exception e) {
            Environment.getLogger().debug("Failed to walk to bank");
        }
    }
}
