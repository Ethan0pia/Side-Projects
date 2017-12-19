package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class IsHealthLow extends BranchTask {

    private DoesInventoryContainFood doesinventorycontainfood;
    private AreWeStuck areWeStuck;
    private Coordinate teleSpot = new Coordinate(2952, 3474, 0);

    private OpiaWineGrabber bot;

    public IsHealthLow(OpiaWineGrabber bot){
        this.bot=bot;
        doesinventorycontainfood = new DoesInventoryContainFood(bot);
        areWeStuck = new AreWeStuck(bot);
    }


    @Override
    public boolean validate() {
        return Health.getCurrentPercent()<60 && Distance.to(teleSpot)>1;
    }

    @Override
    public TreeTask failureTask() {
        return areWeStuck;
    }

    @Override
    public TreeTask successTask() {
        return doesinventorycontainfood;
    }
}
