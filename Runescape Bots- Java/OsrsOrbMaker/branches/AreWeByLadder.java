package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.ClimbLadder;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeByLadder extends BranchTask {

    private ClimbLadder climbLadder;
    private AreWeInsideDungeon areWeInsideDungeon;
    private OsrsOrbMaker bot;
    private Area ladder = new Area.Circular(new Coordinate(3088, 9970,0),4);

    public AreWeByLadder(OsrsOrbMaker bot){
        this.bot=bot;
        climbLadder = new ClimbLadder(bot);
        areWeInsideDungeon = new AreWeInsideDungeon(bot);
    }

    @Override
    public boolean validate() {
        return ladder.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return areWeInsideDungeon;
    }

    @Override
    public TreeTask successTask() {
        return climbLadder;
    }
}
