package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.GrabWine;
import com.ethan0pia.bots.WineGrabber.leaves.RunToTelegrabSpot;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.hybrid.util.calculations.Random;
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
    private Coordinate spot2 = new Coordinate(2951,3473,0);
    private Coordinate spot3 = new Coordinate(2951,3474,0);

    public AreWeInTheTelegrabSpot(OpiaWineGrabber bot){
        this.bot=bot;
        grabWine = new GrabWine(bot);
        runtotelegrabspot = new RunToTelegrabSpot(bot);
    }

    @Override
    public boolean validate() {
        return (bot.getTelegrabSpot().equals(bot.getPlayer().getPosition())|| spot2.equals(bot.getPlayer().getPosition())||spot3.equals(bot.getPlayer().getPosition())) && !bot.getPlayer().isMoving();
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
