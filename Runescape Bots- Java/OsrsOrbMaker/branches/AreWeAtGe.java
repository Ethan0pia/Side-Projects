package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WalkGe;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeAtGe extends BranchTask {

    private HaveWeSoldGlories havewesoldglories;
    private WalkGe walkge;
    private OsrsOrbMaker bot;
    private Area ge = new Area.Circular(new Coordinate(3164,3485,0),10);

    public AreWeAtGe(OsrsOrbMaker bot){
        this.bot=bot;
        havewesoldglories = new HaveWeSoldGlories(bot);
        walkge = new WalkGe(bot);
    }

    @Override
    public boolean validate() {
        return ge.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkge;
    }

    @Override
    public TreeTask successTask() {
        return havewesoldglories;
    }
}
