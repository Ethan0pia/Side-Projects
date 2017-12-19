package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.WalkBank;
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
    private Area bank = new Area.Circular(new Coordinate(2946,3371,0),5);

    private OpiaWineGrabber bot;

    public AreWeAtBank(OpiaWineGrabber bot){
        this.bot=bot;
        walkbank = new WalkBank(bot);
        isbankopen = new IsBankOpen(bot);
    }

    @Override
    public boolean validate() {
        return bank.contains(bot.getPlayer());
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
