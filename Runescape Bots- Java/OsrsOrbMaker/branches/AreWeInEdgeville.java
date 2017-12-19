package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.UseGlory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeInEdgeville extends BranchTask {

    private AreWeByTrapdoor areWeByTrapdoor;
    private UseGlory useGlory;
    private OsrsOrbMaker bot;
    private Area edgeville = new Area.Circular(new Coordinate(3093,3491,0),30);

    public AreWeInEdgeville(OsrsOrbMaker bot){
        this.bot=bot;
        areWeByTrapdoor = new AreWeByTrapdoor(bot);
        useGlory = new UseGlory(bot);
    }

    @Override
    public boolean validate() {
        return edgeville.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return useGlory;
    }

    @Override
    public TreeTask successTask() {
        return areWeByTrapdoor;
    }
}
