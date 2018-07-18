package com.ethan0pia.bots.WineGrabberUltra.grabber.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceContainer;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceContainers;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
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
public class WalkFallyBank extends LeafTask {

    private ActionBar.Slot faladorTele = ActionBar.newQuery().names("Falador Teleport").results().first();
    private Area safeSpot = new Area.Rectangular(new Coordinate(2967,3473,0),new Coordinate(2986,3492,0));
    private OpiaWineGrabberUltra bot;
    private int pathFails = 0;
    private Coordinate oldCoords;

    public WalkFallyBank(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WalkFallyBank");
            bot.setCurrentTask("Walking to the bank.");
            InterfaceComponent bankContainer = Interfaces.newQuery().containers(220).actions("Select").results().first();
            if(bankContainer != null){
                bankContainer.interact("Select");
            }
            Coordinate center = new Coordinate(2945,3369,0);
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
            if (center.isVisible() && Distance.to(center)<6) {
                center.interact("Walk here");
            } else if(player.getHealthGauge()!=null && safeSpot.contains(player)){
                Execution.delayUntil(()->player.getHealthGauge()==null,1000,12000);
            }else {
                WebPath path  = Traversal.getDefaultWeb().getPathBuilder().buildTo(center);
                if (path != null) {
                    if(oldCoords==null){
                        oldCoords = player.getPosition();
                    }
                    if(!path.step()){
                        Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(0.5,0.85));
                    }
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
                                    if(pathFails>8){
                                        player.getPosition().derive(0,4).interact("Walk here");
                                    }
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
