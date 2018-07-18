package com.ethan0pia.bots.WineGrabberUltra.grabber.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.WorldOverview;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class GrabWine extends LeafTask {

    private Coordinate telegrabSpot= new Coordinate(2952, 3473, 0);
    private Area teleSpot = new Area.Circular(new Coordinate(2952, 3473, 0),2);
    private OpiaWineGrabberUltra bot;
    private Coordinate runTo = new Coordinate(2969,3476,0);
    private boolean hovering = false, hoping = false;
    private int wineAfterCast;
    private GroundItem wine;
    private WorldOverview worldToHopTo;

    public GrabWine (OpiaWineGrabberUltra bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("GrabWine");
            ActionBar.Slot telegrabSpell = bot.getTelegrab();
            if (telegrabSpell == null) {
                telegrabSpell = ActionBar.newQuery().names("Telekinetic Grab").results().first();
            }
            Player player = bot.getPlayer();
            if(hoping && player.getHealthGauge()==null){
                hop(player);
            }else {
                if(Lodestone.isInterfaceOpen()){
                    Lodestone.closeInterface();
                }
                if(WorldHop.isOpen()){
                    WorldHop.close();
                }
                bot.setCurrentTask("Waiting for wine to spawn.");
                BresenhamPath path = BresenhamPath.buildTo(runTo);
                if (player.getHealthGauge() == null && ((telegrabSpell != null && telegrabSpell.activate(false)) || Powers.Magic.TELEKINETIC_GRAB.activate())) {
                    if (!hovering && telegrabSpot.hover()) {
                        hovering = true;
                    }
                    int wineAmount = wineAfterCast = Inventory.newQuery().ids(245).results().asList().size();
                    Execution.delayWhile(() -> (wine = GroundItems.newQuery().names("Wine of Zamorak").within(teleSpot).results().first()) == null && player.getHealthGauge() == null, 100, 35000);
                    if (wine != null) {
                        if (wine.interact("Cast")) {
                            hovering = false;
                            if (path != null) {
                                path.getNext().hover();
                            }
                            Execution.delayUntil(() -> {
                                wineAfterCast = Inventory.newQuery().ids(245).results().asList().size();
                                return wineAfterCast > wineAmount || Health.getCurrentPercent() < 45;
                            }, 5000);

                            if (wineAfterCast > wineAmount || player.getHealthGauge() != null) {
                                Execution.delay(550, 600);
                                run(path);
                            } else {
                                bot.setWineLost(bot.getWineLost() + 1);
                                hop(player);
                            }
                        }
                    } else if (player.getHealthGauge() != null) {
                        run(path);
                    }
                } else if (player.getHealthGauge() != null) {
                    run(path);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void run(BresenhamPath path){
        bot.setRun(true);
        if(path!=null) {
            if(path.step()) {
                Execution.delayUntil(() -> bot.getPlayer().isMoving() || Health.getCurrentPercent() < 55, 100, 500);
            }
        }else{
            BresenhamPath backup = BresenhamPath.buildTo(runTo);
            if(backup!=null) {
                if(backup.step()) {
                    Execution.delayUntil(() -> bot.getPlayer().isMoving() || Health.getCurrentPercent() < 55, 100, 500);
                }
            }
        }
    }

    private void hop(Player player) {
        bot.setCurrentTask("Switching worlds.");
        if(player.getHealthGauge()!=null){
            return;
        }
        hoping = true;
        int currHops = bot.getCurrHopWine();
        if (bot.getMaxHopWine() > currHops) {
            int world = Worlds.getCurrent();
            if(worldToHopTo!=null && worldToHopTo.getId()==world) {
                bot.setCurrHopWine(currHops + 1);
                hoping = false;
                worldToHopTo = null;
                Execution.delay(2000,3000);
                setCam();
            }
            if(worldToHopTo == null) {
                worldToHopTo = Worlds.newQuery().free().filter(i -> !i.isPVP() && !i.isMembersOnly() && !i.isLegacyOnly() && i.getId() != Worlds.getCurrent()).results().random();
            }
            if(worldToHopTo!=null && WorldHop.hopTo(worldToHopTo)){
                Execution.delayUntil(()->worldToHopTo.getId() == Worlds.getCurrent(),2000,20000);
                if(worldToHopTo.getId()==Worlds.getCurrent()) {
                    bot.setCurrHopWine(currHops + 1);
                    hoping = false;
                    worldToHopTo = null;
                    Execution.delay(2000,3000);
                    setCam();
                }
            }
        }
    }

    private void setCam(){
        if ((Camera.getYaw() < bot.getCameraYaw()-2 || Camera.getYaw() > bot.getCameraYaw()+2) || Camera.getPitch()<bot.getCameraPitch()-0.05 || Camera.getPitch()>bot.getCameraPitch()+0.05) {
            Camera.concurrentlyTurnTo(bot.getCameraYaw(), bot.getCameraPitch(), 0.1);
        }
    }
}
