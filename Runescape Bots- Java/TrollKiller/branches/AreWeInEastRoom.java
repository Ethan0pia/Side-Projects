package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.ethan0pia.bots.TrollKiller.leaves.WalkToRoom;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeInEastRoom extends BranchTask {

    private IsBowEquipped isBowEquipped;
    private WalkToRoom walktoeastroom;
    private TrollKiller bot;
    private Area eastCave = new Area.Circular(new Coordinate(2229,4384,0),14);

    public AreWeInEastRoom(TrollKiller bot){
        this.bot=bot;
        walktoeastroom = new WalkToRoom(bot, new Coordinate(2229,4384,0));
        isBowEquipped = new IsBowEquipped(bot);

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
        return isBowEquipped;
    }
}
