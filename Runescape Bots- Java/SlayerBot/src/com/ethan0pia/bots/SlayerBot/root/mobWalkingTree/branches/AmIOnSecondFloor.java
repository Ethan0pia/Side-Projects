package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class AmIOnSecondFloor extends BranchTask {

    private AmIAtBeginningSecondFloor amiatbeginningsecondfloor;
    private AmIOnFirstFloor amionfirstfloor;
    private Area floor2 = new Area.Circular(new Coordinate(2042, 5245, 0), 15);
    private Area floor2End = new Area.Circular(new Coordinate(2021, 5217, 0), 15);
    private OpiaSlayer bot;

    public AmIOnSecondFloor(OpiaSlayer bot){
        this.bot=bot;
        amiatbeginningsecondfloor = new AmIAtBeginningSecondFloor(bot);
        amionfirstfloor = new AmIOnFirstFloor(bot);
    }


    @Override
    public boolean validate() {
        return floor2.contains(bot.getPlayer())||floor2End.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amionfirstfloor;
    }

    @Override
    public TreeTask successTask() {
        return amiatbeginningsecondfloor;
    }
}
