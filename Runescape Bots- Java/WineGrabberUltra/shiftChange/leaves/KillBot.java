package com.ethan0pia.bots.WineGrabberUltra.shiftChange.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class KillBot extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private Coordinate end = new Coordinate(3046,3523,0);
    private Coordinate killSpot = new Coordinate(3046,3528,0);
    private Area.Circular wildernessArea = new Area.Circular(end, 10);
    private Coordinate oldCoords = null;
    private boolean beingKilled = false;
    private int pathFails;

    public KillBot(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("killingBot");

            Player player = bot.getPlayer();
            Player killer = Players.newQuery().within(wildernessArea).names(bot.getKiller()).filter(i->i.getPosition().getY()>3520).results().first();
            boolean otherPlayers = !Players.newQuery().within(wildernessArea).filter(i->!i.equals(player) && !i.getName().equals(bot.getKiller()) && i.getPosition().getY()>3520).results().isEmpty();

            if(beingKilled && !wildernessArea.contains(player)){
                bot.setCurrentTask("Going back to grabbing.");
                beingKilled = false;
                bot.setKill(false);
            }else if(!end.equals(player.getPosition()) && killer == null){
                bot.setCurrentTask("Walking near wilderness.");
                walkToEnd(player, end);
            }else if(Worlds.getCurrent()!=bot.getKillWorld()){
                bot.setCurrentTask("Hopping world.");
                WorldHop.hopTo(bot.getKillWorld());
            }else if(killer==null){
                bot.setCurrentTask("Waiting for killer.");
                Execution.delayWhile(Players.newQuery().within(wildernessArea).names(bot.getKiller()).results()::isEmpty,1000,5000);
            }else if(!player.getPosition().equals(killSpot) && !otherPlayers && killer.getPosition().getY()>3520){
                bot.setCurrentTask("Walking to killing spot.");
                beingKilled = true;
                walkToEnd(player, killSpot);
            } else if(otherPlayers){
                bot.setCurrentTask("Other players detected!!!!!");
                if(player.getPosition().equals(end)) {
                    beingKilled = false;
                    Execution.delayUntil(Players.newQuery().within(wildernessArea).filter(i -> i.equals(player) && !i.getName().equals(bot.getKiller()) && i.getPosition().getY()>3520).results()::isEmpty, 1000, 5000);
                }else{
                    walkToEnd(player, end);
                }
            }else{
                bot.setCurrentTask("Checking for other players and waiting to die.");
                Execution.delayWhile(Players.newQuery().within(wildernessArea).filter(i -> i.equals(player) && !i.getName().equals(bot.getKiller())).results()::isEmpty,1000,15000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void walkToEnd(Player player, Coordinate spot) {
        if (spot.isVisible() && spot.getVisibility() > 50 && player.getPosition().getY()>3520) {
            spot.interact("Walk here");
        } else {
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(spot);
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
                        Environment.getLogger().debug("Using alternative path");
                        BresenhamPath pathBackup = BresenhamPath.buildTo(spot);
                        if (pathBackup != null) {
                            pathBackup.step();
                            if (pathFails > 4) {
                                Environment.getLogger().debug("Using viewport path");
                                ViewportPath.convert(pathBackup).step(true);
                            }
                        }
                    }
                }
            } else {
                Environment.getLogger().debug("Using alternative path");
                BresenhamPath pathBackup = BresenhamPath.buildTo(spot);
                if (pathBackup != null) {
                    pathBackup.step();
                }
            }
        }
    }
}
