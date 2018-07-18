package com.ethan0pia.bots.WineGrabberUltra.leveler.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.EnterCave;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.WalkToCaveEntrance;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeAtCaveEntrance extends BranchTask {

    private EnterCave entercave;
    private WalkToCaveEntrance walktocaveentrance;
    private OpiaWineGrabberUltra bot;
    private Area caveEntrance = new Area.Circular(new Coordinate(2878,3572,0),5);

    public AreWeAtCaveEntrance(OpiaWineGrabberUltra bot){
        this.bot=bot;
        entercave = new EnterCave(bot);
        walktocaveentrance = new WalkToCaveEntrance(bot);
    }


    @Override
    public boolean validate() {
        return caveEntrance.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walktocaveentrance;
    }

    @Override
    public TreeTask successTask() {
        return entercave;
    }
}
