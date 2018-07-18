package com.ethan0pia.bots.WineGrabberUltra.shiftChange.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkEdgeBank extends LeafTask {

    private Area safeSpot = new Area.Rectangular(new Coordinate(2967,3473,0),new Coordinate(2986,3492,0));
    private OpiaWineGrabberUltra bot;
    private int pathFails = 0;
    private Coordinate oldCoords;

    public WalkEdgeBank(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WalkEdgeBank");
            bot.setCurrentTask("Walking to the bank.");
            Coordinate center = new Coordinate(3097, 3496, 0);
            Player player = bot.getPlayer();
            if (center.isVisible()) {
                center.interact("Walk here");
            } else if (player.getHealthGauge() != null && safeSpot.contains(player)) {
                Execution.delayUntil(() -> player.getHealthGauge() == null, 1000, 12000);
            } else {
                WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(center);
                if (path != null) {
                    if (oldCoords == null) {
                        oldCoords = player.getPosition();
                    }
                    if (!path.step()) {
                        Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(0.5,0.85));
                    }
                    if (!oldCoords.equals(player.getPosition())) {
                        Environment.getLogger().debug("Moved");
                        pathFails = 0;
                        oldCoords = player.getPosition();
                    } else {
                        pathFails++;
                        Environment.getLogger().debug("failed to move " + pathFails);
                        if (pathFails > 2) {
                            Environment.getLogger().debug("Using alternative path to get to bank");
                            BresenhamPath pathBackup = BresenhamPath.buildTo(center);
                            if (pathBackup != null) {
                                pathBackup.step();
                                if (pathFails > 4) {
                                    Environment.getLogger().debug("Using viewport path to get to bank");
                                    ViewportPath viewPath = ViewportPath.convert(pathBackup);
                                    if(!viewPath.step()){
                                        Camera.concurrentlyTurnTo(viewPath.getNext(), Random.nextDouble(0.5,0.85));
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Environment.getLogger().debug("Using alternative path to get to bank");
                    BresenhamPath pathBackup = BresenhamPath.buildTo(center);
                    if (pathBackup != null) {
                        pathBackup.step();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
