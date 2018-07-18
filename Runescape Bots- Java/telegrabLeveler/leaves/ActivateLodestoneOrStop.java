package com.ethan0pia.bots.TelegrabLeveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ActivateLodestoneOrStop extends LeafTask {

    private TelegrabLeveler bot;
    private Area edgevilleLode = new Area.Circular(Lodestone.EDGEVILLE.getPosition(),6);
    private Area fallyLode = new Area.Circular(new Coordinate(2967,3406,0),4);
    private Area cave = new Area.Circular(new Coordinate(2209,4376,0),30);
    private Coordinate oldCoords;
    private int pathFails = 0;
    private int teleFails = 0;
    private Coordinate fally = new Coordinate(2966, 3404, 0);
    private Coordinate edge = new Coordinate(3067, 3505, 0);

    public ActivateLodestoneOrStop(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Player player = bot.getPlayer();
            if (!Lodestone.FALADOR.isActivated()) {
                if (!fallyLode.contains(bot.getPlayer())) {
                    WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(fally);
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
                                Environment.getLogger().debug("Using alternative path");
                                if(cave.contains(player)){
                                    if(Lodestone.TAVERLEY.teleport()) {
                                        Execution.delayWhile(() -> cave.contains(player), 30000);
                                    }
                                }else {
                                    BresenhamPath pathBackup = BresenhamPath.buildTo(fally);
                                    if(pathBackup!=null) {
                                        pathBackup.step();
                                        if(pathFails>4){
                                            Environment.getLogger().debug("Using viewport path");
                                            ViewportPath.convert(pathBackup).step();
                                        }
                                    }
                                }
                            }
                        }
                    } else{
                        Environment.getLogger().debug("Using alternative path");
                        if(cave.contains(player)){
                            if(Lodestone.BURTHORPE.teleport()) {
                                Execution.delayWhile(() -> cave.contains(player), 30000);
                            }
                        }else {
                            BresenhamPath pathBackup = BresenhamPath.buildTo(fally);
                            if (pathBackup != null) {
                                ViewportPath.convert(pathBackup).step();
                            }
                        }
                    }
                } else {
                    if (Lodestone.FALADOR.getPosition().interact("Activate") || Lodestone.FALADOR.getPosition().click()) {
                        Execution.delay(5000, 7000);
                    }
                }
            } else if (!Lodestone.EDGEVILLE.isActivated()) {
                if (!edgevilleLode.contains(bot.getPlayer())) {
                    WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(edge);
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
                                Environment.getLogger().debug("Using alternative path");
                                if(cave.contains(player)){
                                    if(Lodestone.FALADOR.teleport()) {
                                        Execution.delayWhile(() -> cave.contains(player), 30000);
                                    }
                                }else {
                                    BresenhamPath pathBackup = BresenhamPath.buildTo(edge);
                                    if(pathBackup!=null) {
                                        pathBackup.step();
                                        if(pathFails>4){
                                            Environment.getLogger().debug("Using viewport path");
                                            ViewportPath.convert(pathBackup).step();
                                        }
                                    }
                                }
                            }
                        }
                    } else{
                        Environment.getLogger().debug("Using alternative path");
                        if(cave.contains(player)){
                            if(Lodestone.FALADOR.teleport()) {
                                Execution.delayWhile(() -> cave.contains(player), 30000);
                            }
                        }else {
                            BresenhamPath pathBackup = BresenhamPath.buildTo(edge);
                            if (pathBackup != null) {
                                pathBackup.step();
                            }
                        }
                    }
                } else {
                    if (Lodestone.EDGEVILLE.getPosition().interact("Activate") || Lodestone.EDGEVILLE.getPosition().click()) {
                        Execution.delay(5000, 7000);
                    }
                }
            } else {
                if (Lodestone.FALADOR.teleport()||teleFails >= 5) {
                    bot.stop("Tasks completed");
                }else{
                    teleFails++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
