package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class AreWeInLumbridge extends BranchTask {

    private HaveWeSwitchedWorlds haveweswitchedworlds;
    private DoWeHaveTheStuff dowehavethestuff;
    private OsrsOrbMaker bot;
    private Area groundFloor = new Area.Circular(new Coordinate(3214,3216,0),30);
    private Area firstFloor = new Area.Circular(new Coordinate(3214,3216,1),30);
    private Area secondFloor = new Area.Circular(new Coordinate(3214,3216,2),30);

    public AreWeInLumbridge(OsrsOrbMaker bot){
        this.bot=bot;
        haveweswitchedworlds = new HaveWeSwitchedWorlds(bot);
        dowehavethestuff = new DoWeHaveTheStuff(bot);
    }

    @Override
    public boolean validate() {
        Player player = bot.getPlayer();
        return groundFloor.contains(player)||groundFloor.contains(firstFloor)||groundFloor.contains(secondFloor);
    }

    @Override
    public TreeTask failureTask() {
        return dowehavethestuff;
    }

    @Override
    public TreeTask successTask() {
        return haveweswitchedworlds;
    }
}
