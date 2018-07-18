package com.ethan0pia.bots.WineGrabberUltra.grabber.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.GrabWine;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.RunToTelegrabSpot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeInTheTelegrabSpot extends BranchTask {

    private GrabWine grabWine;
    private RunToTelegrabSpot runtotelegrabspot;
    private OpiaWineGrabberUltra bot;

    AreWeInTheTelegrabSpot(OpiaWineGrabberUltra bot){
        this.bot=bot;
        grabWine = new GrabWine(bot);
        runtotelegrabspot = new RunToTelegrabSpot(bot);
    }

    @Override
    public boolean validate() {
        Player player = bot.getPlayer();
        return bot.getTelegrabSpot().equals(player.getPosition());
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
