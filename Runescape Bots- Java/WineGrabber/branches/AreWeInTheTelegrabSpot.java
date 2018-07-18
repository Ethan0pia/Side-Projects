package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.GrabWine;
import com.ethan0pia.bots.WineGrabber.leaves.RunToTelegrabSpot;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeInTheTelegrabSpot extends BranchTask {

    private GrabWine grabWine;
    private RunToTelegrabSpot runtotelegrabspot;
    private OpiaWineGrabber bot;

    AreWeInTheTelegrabSpot(OpiaWineGrabber bot){
        this.bot=bot;
        grabWine = new GrabWine(bot);
        runtotelegrabspot = new RunToTelegrabSpot(bot);
    }

    @Override
    public boolean validate() {
        Environment.getLogger().debug("AreWeInTheTelegrabSpot");
        return bot.getTelegrabSpot().equals(bot.getPlayer().getPosition()) && !bot.getPlayer().isMoving();
    }

    @Override
    public TreeTask failureTask() {
        return runtotelegrabspot;
    }

    @Override
    public TreeTask successTask() {
        return grabWine;
    }
}
