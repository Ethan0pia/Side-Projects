package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.WalkMob;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIInZammyRoom extends BranchTask {

    private AreWeInGWD ispathtocaveobstaclenull;
    private WalkMob walkmob;
    private OpiaSpiritualMages bot;
    private Area zammyRoom=new Area.Rectangular(new Coordinate(2883,5341,0),new Coordinate(2940,5365,0));

    public AmIInZammyRoom(OpiaSpiritualMages bot){
        this.bot=bot;
        ispathtocaveobstaclenull = new AreWeInGWD(bot);
        walkmob = new WalkMob(bot);
    }

    @Override
    public boolean validate() {
        return zammyRoom.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return ispathtocaveobstaclenull;
    }

    @Override
    public TreeTask successTask() {
        return walkmob;
    }
}
