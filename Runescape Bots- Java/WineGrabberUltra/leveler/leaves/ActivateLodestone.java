package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ActivateLodestone extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private Area fallyLode = new Area.Circular(new Coordinate(2967,3406,0),4);
    private Area cave = new Area.Circular(new Coordinate(2209,4376,0),30);
    private Coordinate oldCoords;
    private int pathFails = 0;
    private Coordinate fally = new Coordinate(2966, 3404, 0);

    public ActivateLodestone(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("ActivateLodestone");
            if(bot.isReturnToWorld() && Worlds.getCurrent()!=bot.getOriginalWorld()){
                WorldHop.hopTo(bot.getOriginalWorld());
            }else {
                Varbit spellId = Varbits.load(36325);
                bot.setCurrentTask("Activating Falador lodestone.");
                Player player = bot.getPlayer();
                if (ActionBar.isAutoRetaliating()) {
                    ActionBar.toggleAutoRetaliation();
                } else if(spellId!= null && (spellId.getValue()==46 || spellId.getValue() == 47)){
                    if (Powers.Magic.AIR_BOLT.activate()) {
                        Execution.delayUntil(()->0 == Varbits.load(36325).getValue(),5000);
                    }
                }else if (!fallyLode.contains(bot.getPlayer())) {
                    WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(fally);
                    if (path != null) {
                        if (oldCoords == null) {
                            oldCoords = player.getPosition();
                        }
                        path.step();
                        if (!oldCoords.equals(player.getPosition())) {
                            Environment.getLogger().debug("Moved");
                            pathFails = 0;
                            oldCoords = player.getPosition();
                        } else {
                            pathFails++;
                            Environment.getLogger().debug("failed to move " + pathFails + " times");
                            if (pathFails > 2) {
                                Environment.getLogger().debug("Using alternative path");
                                if (cave.contains(player)) {
                                    if (Lodestone.TAVERLEY.teleport()) {
                                        Execution.delayWhile(() -> cave.contains(player), 30000);
                                    }
                                } else {
                                    BresenhamPath pathBackup = BresenhamPath.buildTo(fally);
                                    if (pathBackup != null) {
                                        pathBackup.step();
                                        if (pathFails > 4) {
                                            Environment.getLogger().debug("Using viewport path");
                                            ViewportPath.convert(pathBackup).step();
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Environment.getLogger().debug("Using alternative path");
                        if (cave.contains(player)) {
                            if (Lodestone.BURTHORPE.teleport()) {
                                Execution.delayWhile(() -> cave.contains(player), 30000);
                            }
                        } else {
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
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
