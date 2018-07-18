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
public class AreWeInNorthRoom extends BranchTask {

    private IsSwordEquipped isSwordEquipped;
    private WalkToRoom walkToRoom;
    private TrollKiller bot;
    private Area northCave = new Area.Circular(new Coordinate(2208,4401,0),14);

    public AreWeInNorthRoom(TrollKiller bot){
        this.bot=bot;
        walkToRoom = new WalkToRoom(bot, new Coordinate(2208,4401,0));
        isSwordEquipped = new IsSwordEquipped(bot);

    }


    @Override
    public boolean validate() {
        return northCave.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkToRoom;
    }

    @Override
    public TreeTask successTask() {
        return isSwordEquipped;
    }
}
