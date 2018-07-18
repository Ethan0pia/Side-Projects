package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
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

    private DoWeHaveFood doWeHaveFood;
    private CanWeGrabMore canWeGrabMore;
    private Coordinate teleSpot = new Coordinate(2952, 3474, 0);

    private OpiaWineGrabber bot;

    public IsHealthLow(OpiaWineGrabber bot){
        this.bot=bot;
        doWeHaveFood = new DoWeHaveFood(bot);
        canWeGrabMore = new CanWeGrabMore(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("IsHealthLow");
        int health = Health.getCurrentPercent();
        return (health<60 && Distance.to(teleSpot)>1) || health<30;
    }

    @Override
    public TreeTask failureTask() {
        return canWeGrabMore;
    }

    @Override
    public TreeTask successTask() {
        return doWeHaveFood;
    }
}
