package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkBank extends LeafTask {

    private Area bank = new Area.Rectangular(new Coordinate(2945,3370,0),new Coordinate(2946,3368,0));
    private ActionBar.Slot faladorTele = ActionBar.newQuery().names("Falador Teleport").results().first();
    private OpiaWineGrabber bot;

    public WalkBank(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            if (faladorTele == null) {
                faladorTele = ActionBar.newQuery().names("Falador Teleport").results().first();
            }
            if (faladorTele != null && Distance.to(bank.getRandomCoordinate()) > 30 && faladorTele.isActivatable()) {
                Execution.delay(500, 1000);
                if (faladorTele.activate(false)) {
                    Execution.delayUntil(() -> Distance.to(bank.getRandomCoordinate()) < 30);
                }
            }
            WebPath path = null;
            try {
                path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bank.getCenter());
            } catch (Exception e) {
                Environment.getLogger().debug("Pathing failed");
            }
            if (path != null) {
                Execution.delayUntil(path::step, 1000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        bot.setLeafId(5);
    }
}
