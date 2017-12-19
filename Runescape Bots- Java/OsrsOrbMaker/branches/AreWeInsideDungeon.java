package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.RunLadder;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeInsideDungeon extends BranchTask {

    private RunLadder runladder;
    private AreWeInEdgeville areWeInEdgeville;
    private OsrsOrbMaker bot;
    private Area dungeon = new Area.Circular(new Coordinate(3104,9911,0),150);

    public AreWeInsideDungeon(OsrsOrbMaker bot){
        this.bot=bot;
        runladder = new RunLadder(bot);
        areWeInEdgeville = new AreWeInEdgeville(bot);
    }

    @Override
    public boolean validate() {
        return dungeon.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return areWeInEdgeville;
    }

    @Override
    public TreeTask successTask() {
        return runladder;
    }
}
