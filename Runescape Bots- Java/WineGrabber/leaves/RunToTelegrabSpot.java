package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class RunToTelegrabSpot extends LeafTask {

    private OpiaWineGrabber bot;

    public RunToTelegrabSpot(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Coordinate telegrabspot = bot.getTelegrabSpot();
            if (telegrabspot != null) {
                Player player = bot.getPlayer();
                boolean visible = telegrabspot.isVisible();
                if (visible && !bot.getPlayer().isMoving()) {
                    if (telegrabspot.interact("Walk here")) {
                        Execution.delayUntil(() -> player.isMoving() || player.getHealthGauge() != null, 1000);
                        Execution.delayWhile(() -> player.isMoving() && Health.getCurrentPercent() > 40, 3000);
                    } else {
                        if (telegrabspot.click()) {
                            Execution.delayUntil(() -> player.isMoving() || player.getHealthGauge() != null, 1000);
                            Execution.delayWhile(() -> player.isMoving() && Health.getCurrentPercent() > 40, 3000);
                        }
                    }
                } else if (!visible) {
                    WebPath path = null;
                    try {
                        path = Traversal.getDefaultWeb().getPathBuilder().buildTo(telegrabspot);
                    } catch (Exception e) {
                        Environment.getLogger().debug("Pathing failed");
                    }
                    if (path != null) {
                        Execution.delayUntil(path::step, 1000);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        bot.setLeafId(4);
    }
}
