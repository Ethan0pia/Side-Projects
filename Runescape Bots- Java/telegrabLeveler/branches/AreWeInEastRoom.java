package com.ethan0pia.bots.TelegrabLeveler.branches;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.TelegrabLeveler.leaves.WalkToEastRoom;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeInEastRoom extends BranchTask {

    private IsStaffOnGround isstaffonground;
    private WalkToEastRoom walktoeastroom;
    private TelegrabLeveler bot;
    private Area eastCave = new Area.Circular(new Coordinate(2229,4384,0),14);

    public AreWeInEastRoom(TelegrabLeveler bot){
        this.bot=bot;
        walktoeastroom = new WalkToEastRoom(bot);
        isstaffonground = new IsStaffOnGround(bot);

    }


    @Override
    public boolean validate() {
        return eastCave.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walktoeastroom;
    }

    @Override
    public TreeTask successTask() {
        return isstaffonground;
    }
}
