package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.RunSafeSpot;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class StillAtZammyArea extends BranchTask {

    private AreWeAtBank areWeAtBank;
    private RunSafeSpot runsafespot;
    private Area church = new Area.Rectangular(new Coordinate(2963,3478,0),new Coordinate(2949,3473,0));

    private OpiaWineGrabber bot;

    public StillAtZammyArea(OpiaWineGrabber bot){
        this.bot=bot;
        areWeAtBank = new AreWeAtBank(bot);
        runsafespot = new RunSafeSpot(bot);
    }


    @Override
    public boolean validate() {
        return church.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return areWeAtBank;
    }

    @Override
    public TreeTask successTask() {
        return runsafespot;
    }
}
