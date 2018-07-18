package com.ethan0pia.bots.TelegrabLeveler.branches;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.TelegrabLeveler.leaves.EnterCave;
import com.ethan0pia.bots.TelegrabLeveler.leaves.WalkToCaveEntrance;
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
    private TelegrabLeveler bot;
    private Area caveEntrance = new Area.Circular(new Coordinate(2878,3572,0),5);

    public AreWeAtCaveEntrance(TelegrabLeveler bot){
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
