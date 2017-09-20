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
public class AmIOnFirstFloor extends BranchTask {

    private AmIAtBeginningFirstFloor amiatbeginningfirstfloor;
    private AmIAtStrongholdEntrance amIInsideStronghold;
    private Area floor1 = new Area.Circular(new Coordinate(1859, 5243, 0), 15);
    private Area floor1End = new Area.Circular(new Coordinate(1908, 5222, 0), 15);
    private OpiaSlayer bot;

    public AmIOnFirstFloor(OpiaSlayer bot){
        this.bot=bot;
        amIInsideStronghold = new AmIAtStrongholdEntrance(bot);
        amiatbeginningfirstfloor = new AmIAtBeginningFirstFloor(bot);
    }


    @Override
    public boolean validate() {
        return floor1.contains(bot.getPlayer())|| floor1End.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amIInsideStronghold;
    }

    @Override
    public TreeTask successTask() {
        return amiatbeginningfirstfloor;
    }
}
