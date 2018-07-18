package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkBank extends LeafTask {

    private ActionBar.Slot faladorTele = ActionBar.newQuery().names("Falador Teleport").results().first();
    private Area safeSpot = new Area.Rectangular(new Coordinate(2967,3473,0),new Coordinate(2986,3492,0));
    private OpiaWineGrabber bot;
    private int pathFails = 0;
    private Coordinate oldCoords;

    public WalkBank(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WalkFallyBank");
            Coordinate center;
            switch(bot.getBankLocation()){
                case 0:
                    center = new Coordinate(2945,3369,0);
                    break;
                case 1:
                    center = new Coordinate(2875,3417,0);
                    break;
                default:
                    center = new Coordinate(3097,3496,0);
                    break;
            }
            Player player = bot.getPlayer();
            if (bot.isUseTeleport()) {
                Coordinate fallyBank = new Coordinate(2945,3369,0);
                if (faladorTele == null) {
                    faladorTele = ActionBar.newQuery().names("Falador Teleport").results().first();
                }
                if (faladorTele != null && Distance.to(fallyBank) > 30 && faladorTele.isActivatable()) {
                    Execution.delay(500, 1000);
                    if (faladorTele.activate(false)) {
                        Execution.delayUntil(() -> Distance.to(fallyBank) < 30);
                    }
                }
            }
            if (center.isVisible()) {
                center.interact("Walk here");
            } else if(player.getHealthGauge()!=null && bot.getBankLocation()==0 && safeSpot.contains(player)){
                Execution.delayUntil(()->player.getHealthGauge()==null,1000,12000);
            }else {
                WebPath path  = Traversal.getDefaultWeb().getPathBuilder().buildTo(center);
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
                        Environment.getLogger().debug("failed to move "+ pathFails);
                        if(pathFails > 2) {
                            Environment.getLogger().debug("Using alternative path to get to bank");
                            BresenhamPath pathBackup = BresenhamPath.buildTo(center);
                            if(pathBackup!=null) {
                                pathBackup.step();
                                if(pathFails>4){
                                    Environment.getLogger().debug("Using viewport path to get to bank");
                                    ViewportPath.convert(pathBackup).step();
                                }
                            }
                        }
                    }
                } else{
                    Environment.getLogger().debug("Using alternative path to get to bank");
                    BresenhamPath pathBackup = BresenhamPath.buildTo(center);
                    if(pathBackup!=null) {
                        pathBackup.step();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
