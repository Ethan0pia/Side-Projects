package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WalkTrapdoor;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeByTrapdoor extends BranchTask {

    private IsTrapdoorOpen istrapdooropen;
    private WalkTrapdoor walktrapdoor;
    private OsrsOrbMaker bot;
    private Area trapdoor = new Area.Circular(new Coordinate(3095,3469,0),5);

    public AreWeByTrapdoor(OsrsOrbMaker bot){
        this.bot=bot;
        istrapdooropen = new IsTrapdoorOpen(bot);
        walktrapdoor = new WalkTrapdoor(bot);
    }

    @Override
    public boolean validate() {
        return trapdoor.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walktrapdoor;
    }

    @Override
    public TreeTask successTask() {
        return istrapdooropen;
    }
}
