package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.rs3.local.hud.Powers;
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
    private OpiaWineGrabber bot;
    private ActionBar.Slot telegrabSpell=null;
    private Area runTo = new Area.Circular(new Coordinate(2971,3477,0),3);
    private boolean hovering = false;
    private int wineAfterCast;
    private Area safeSpot = new Area.Rectangular(new Coordinate(2967,3473,0),new Coordinate(2986,3492,0));
    GroundItem wine;

    public GrabWine (OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            if (telegrabSpell == null) {
                telegrabSpell = ActionBar.newQuery().names("Telekinetic Grab").results().first();
            }
            Player player = bot.getPlayer();
            if ((telegrabSpell != null && telegrabSpell.activate(false)) || Powers.Magic.TELEKINETIC_GRAB.activate()) {
                Coordinate runToSpot = runTo.getRandomCoordinate();
                if (!hovering && telegrabSpot.hover()) {
                    hovering = true;
                    Execution.delay(200, 250);
                }
                int wineAmount = wineAfterCast = Inventory.newQuery().ids(245).results().asList().size();
                Execution.delayWhile(() -> (wine = GroundItems.newQuery().names("Wine of Zamorak").within(teleSpot).results().first()) == null && player.getHealthGauge() == null, 35000);
                if (wine != null) {
                    if (wine.interact("Cast")) {
                        hovering = false;
                        Execution.delayUntil(() -> {
                            wineAfterCast = Inventory.newQuery().ids(245).results().asList().size();
                            return wineAfterCast > wineAmount || Health.getCurrentPercent() < 40;
                        }, 5000);

                        if (wineAfterCast > wineAmount || player.getHealthGauge() != null) {
                            bot.setRun(true);
                            BresenhamPath path = BresenhamPath.buildTo(runToSpot);
                            Execution.delayUntil(() -> path.step() && bot.getPlayer().isMoving() || Health.getCurrentPercent() < 45, 2000);

                        } else {
                            bot.setWineLost(bot.getWineLost() + 1);
                        }
                    }
                } else if (player.getHealthGauge() != null) {
                    bot.setRun(true);
                    BresenhamPath path = BresenhamPath.buildTo(runToSpot);
                    Execution.delayUntil(() -> path.step() && bot.getPlayer().isMoving() || Health.getCurrentPercent() < 45, 2000);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        bot.setLeafId(1);
    }
}
