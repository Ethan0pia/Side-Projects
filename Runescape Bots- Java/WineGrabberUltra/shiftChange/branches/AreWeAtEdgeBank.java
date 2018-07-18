package com.ethan0pia.bots.WineGrabberUltra.shiftChange.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.shiftChange.leaves.WalkEdgeBank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeAtEdgeBank extends BranchTask {

    private WalkEdgeBank walkEdgeville;
    private IsBankOpen isBankOpen;
    private Area edgeBank = new Area.Rectangular(new Coordinate(3094,3498,0),new Coordinate(3098,3496,0));
    private OpiaWineGrabberUltra bot;

    public AreWeAtEdgeBank(OpiaWineGrabberUltra bot){
        this.bot=bot;
        walkEdgeville = new WalkEdgeBank(bot);
        isBankOpen = new IsBankOpen(bot);
    }

    @Override
    public boolean validate() {
        return edgeBank.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkEdgeville;
    }

    @Override
    public TreeTask successTask() {
        return isBankOpen;
    }
}
