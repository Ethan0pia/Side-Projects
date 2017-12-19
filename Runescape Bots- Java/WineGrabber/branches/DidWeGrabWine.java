package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.RunSafeSpot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DidWeGrabWine extends BranchTask {

    private RunSafeSpot runsafespot;
    private AreWeInTheTelegrabSpot areweinthetelegrabspot;
    private Area safeSpot = new Area.Rectangular(new Coordinate(2967,3473,0),new Coordinate(2986,3492,0));

    private OpiaWineGrabber bot;

    public DidWeGrabWine(OpiaWineGrabber bot){
        this.bot=bot;
        areweinthetelegrabspot = new AreWeInTheTelegrabSpot(bot);
        runsafespot = new RunSafeSpot(bot);
    }



    @Override
    public boolean validate() {
        if (bot.isRun() && safeSpot.contains(bot.getPlayer())) {
            bot.setRun(false);
        }
        return bot.isRun();
    }

    @Override
    public TreeTask failureTask() {
        return areweinthetelegrabspot;
    }

    @Override
    public TreeTask successTask() {
        return runsafespot;
    }
}
