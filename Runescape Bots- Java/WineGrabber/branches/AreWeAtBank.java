package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.WalkBank;
import com.runemate.game.api.hybrid.Environment;
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
    private WalkBank walkbank;
    private Area fallyBank = new Area.Rectangular(new Coordinate(2944,3370,0),new Coordinate(2947,3368,0));
    private Area taverlyBank = new Area.Rectangular(new Coordinate(2875,3418,0),new Coordinate(2877,3415,0));
    private Area edgeBank = new Area.Rectangular(new Coordinate(3094,3498,0),new Coordinate(3098,3496,0));

    private OpiaWineGrabber bot;

    public AreWeAtBank(OpiaWineGrabber bot){
        this.bot=bot;
        walkbank = new WalkBank(bot);
        isbankopen = new IsBankOpen(bot);
    }

    @Override
    public boolean validate() {
        Environment.getLogger().debug("AreWeAtBank");
        switch(bot.getBankLocation()){
            case 0:
                return fallyBank.contains(bot.getPlayer());
            case 1:
                return taverlyBank.contains(bot.getPlayer());
            default:
                return edgeBank.contains(bot.getPlayer());
        }
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
