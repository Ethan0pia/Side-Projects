package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class RunSafeSpot extends LeafTask {

    private Area safeSpot = new Area.Rectangular(new Coordinate(2967,3473,0),new Coordinate(2986,3492,0));
    private OpiaWineGrabber bot;
    private Area runTo = new Area.Circular(new Coordinate(2971,3477,0),3);

    public RunSafeSpot(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Player player = bot.getPlayer();
            Camera.turnTo(28, Random.nextDouble(0.84, 0.843), 0.1);
            if (!safeSpot.contains(player)) {
                WebPath path = null;
                try {
                    path = Traversal.getDefaultWeb().getPathBuilder().buildTo(runTo.getRandomCoordinate());
                } catch (Exception e) {
                    Environment.getLogger().debug("Pathing failed");
                }
                if (path != null && !safeSpot.contains(player)) {
                    if (path.step()) {
                        Execution.delayUntil(() -> player.isMoving() || Health.getCurrentPercent() < 40, 1000);
                        Execution.delayWhile(() -> !safeSpot.contains(player) && Health.getCurrentPercent() > 40, 3000);
                    }
                } else if (!safeSpot.contains(player)) {
                    BresenhamPath backupPath = BresenhamPath.buildTo(runTo.getRandomCoordinate());
                    if (backupPath.step()) {
                        Execution.delayUntil(() -> player.isMoving() || Health.getCurrentPercent() < 40, 1000);
                        Execution.delayWhile(() -> !safeSpot.contains(player) && Health.getCurrentPercent() > 40, 3000);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        bot.setLeafId(3);
    }
}
