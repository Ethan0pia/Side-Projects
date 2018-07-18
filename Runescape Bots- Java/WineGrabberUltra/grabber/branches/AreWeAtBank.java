package com.ethan0pia.bots.WineGrabberUltra.grabber.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.WalkFallyBank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeAtBank extends BranchTask {

    private IsBankOpen isbankopen;
    private WalkFallyBank walkbank;
    private Area fallyBank = new Area.Rectangular(new Coordinate(2944,3370,0),new Coordinate(2947,3368,0));
    //private Area edgeBank = new Area.Rectangular(new Coordinate(3094,3498,0),new Coordinate(3098,3496,0));

    private OpiaWineGrabberUltra bot;

    public AreWeAtBank(OpiaWineGrabberUltra bot){
        this.bot=bot;
        walkbank = new WalkFallyBank(bot);
        isbankopen = new IsBankOpen(bot);
    }

    @Override
    public boolean validate() {
        return fallyBank.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkbank;
    }

    @Override
    public TreeTask successTask() {
        return isbankopen;
    }
}
