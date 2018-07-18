package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class RunToTelegrabSpot extends LeafTask {

    private OpiaWineGrabber bot;
    private int pathFails = 0;
    private Coordinate oldCoords;

    public RunToTelegrabSpot(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("RunToTelegrabSpot");
            Coordinate telegrabspot = bot.getTelegrabSpot();
            if (telegrabspot != null) {
                Player player = bot.getPlayer();
                boolean visible = telegrabspot.isVisible();
                if (visible && !bot.getPlayer().isMoving()) {
                    Environment.getLogger().debug("Spot is Visible");
                    if (telegrabspot.interact("Walk here")) {
                        move(player);
                    } else {
                        if (telegrabspot.click()) {
                            move(player);
                        }
                    }
                } else if (!visible) {
                    WebPath path;
                    if(bot.getBankLocation()!=0){
                        path = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(telegrabspot);
                    }else {
                        path = Traversal.getDefaultWeb().getPathBuilder().buildTo(telegrabspot);
                    }
                    if (path != null) {
                        if(oldCoords==null){
                            oldCoords = player.getPosition();
                        }
                        path.step();
                        if(!oldCoords.equals(player.getPosition())){
                            Environment.getLogger().debug("Moved");
                            pathFails=0;
                            oldCoords = player.getPosition();
                        }else{
                            pathFails++;
                            Environment.getLogger().debug("failed to move "+ pathFails + " times");
                            if(pathFails > 2) {
                                Environment.getLogger().debug("Using alternative path to get to telegrab spot");
                                BresenhamPath pathBackup = BresenhamPath.buildTo(telegrabspot);
                                if(pathBackup!=null) {
                                    pathBackup.step();
                                    if(pathFails>4){
                                        Environment.getLogger().debug("Using viewport path to get to telegrab spot");
                                        ViewportPath.convert(pathBackup).step();
                                    }
                                }
                            }
                        }
                    } else {
                        Environment.getLogger().debug("Using alternative path to get to telegrab spot");
                        BresenhamPath pathBackup = BresenhamPath.buildTo(telegrabspot);
                        if(pathBackup!=null) {
                            ViewportPath.convert(pathBackup).step();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void move(Player player){
        Execution.delayUntil(() -> player.isMoving() || player.getHealthGauge() != null, 100,1000);
        Execution.delayWhile(() -> player.isMoving() && Health.getCurrentPercent() > 45, 250,3000);
    }
}
