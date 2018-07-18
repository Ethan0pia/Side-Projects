package com.ethan0pia.bots.WineGrabberUltra.grabber.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
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
    private DidWeGrabWine didWeGrabWine;
    private Coordinate teleSpot = new Coordinate(2952, 3474, 0);

    private OpiaWineGrabberUltra bot;

    public IsHealthLow(OpiaWineGrabberUltra bot){
        this.bot=bot;
        doWeHaveFood = new DoWeHaveFood(bot);
        didWeGrabWine = new DidWeGrabWine(bot);
    }


    @Override
    public boolean validate() {
        int health = Health.getCurrentPercent();
        return (health<60 && Distance.to(teleSpot)>1) || health<50;
    }

    @Override
    public TreeTask failureTask() {
        return didWeGrabWine;
    }

    @Override
    public TreeTask successTask() {
        return doWeHaveFood;
    }
}
