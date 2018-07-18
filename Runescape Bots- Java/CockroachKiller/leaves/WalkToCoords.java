package com.ethan0pia.bots.CockroachKiller.leaves;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.concurrent.TimeUnit;

/**
 * NOTES:
 * 
 */
public class WalkToCoords extends LeafTask {

    private Roach bot;
    private Coordinate coordsToWalkTo;
    private Coordinate oldCoords;
    private int pathFails = 0;
    private Area.Circular area = new Area.Circular(new Coordinate(3094,3491,0),100);

    public WalkToCoords(Roach bot, Coordinate coords){
        this.bot=bot;
        coordsToWalkTo=coords;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("15");
            if(!area.contains(bot.getPlayer())){
                if(Lodestone.EDGEVILLE.teleport()){
                    Execution.delayUntil(()->area.contains(bot.getPlayer()),200,30000);
                }
            }else if (coordsToWalkTo != null) {
                Player player = bot.getPlayer();
                boolean visible = coordsToWalkTo.isVisible();
                if (visible && !bot.getPlayer().isMoving()) {
                    if (coordsToWalkTo.interact("Walk here")) {
                        move(player);
                    } else {
                        if (coordsToWalkTo.click()) {
                            move(player);
                        }
                    }
                } else if (!visible) {
                    WebPath path = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(coordsToWalkTo);
                    if (path != null) {
                        if(oldCoords==null){
                            oldCoords = player.getPosition();
                        }
                        path.step();
                        if(!oldCoords.equals(player.getPosition())){
                            pathFails=0;
                            oldCoords = player.getPosition();
                        }else{
                            pathFails++;
                            if(pathFails > 2) {
                                BresenhamPath pathBackup = BresenhamPath.buildTo(coordsToWalkTo);
                                if(pathBackup!=null) {
                                    pathBackup.step();
                                    if(pathFails>4){
                                        ViewportPath.convert(pathBackup).step();
                                    }
                                }
                            }
                        }
                    } else {
                        BresenhamPath pathBackup = BresenhamPath.buildTo(coordsToWalkTo);
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
