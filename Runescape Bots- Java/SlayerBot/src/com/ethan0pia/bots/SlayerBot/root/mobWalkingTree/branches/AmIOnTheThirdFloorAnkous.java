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
public class AmIOnTheThirdFloorAnkous extends BranchTask {

    private AmIAtBeginningOfThirdFloor amiatbeginningofthirdfloor;
    private AmIOnSecondFloor amionsecondfloor;
    private Area floor3 = new Area.Circular(new Coordinate(2123, 5252, 0), 15);
    private Area floor3End = new Area.Circular(new Coordinate(2144, 5281, 0), 15);
    private OpiaSlayer bot;

    public AmIOnTheThirdFloorAnkous(OpiaSlayer bot){
        this.bot=bot;
        amiatbeginningofthirdfloor = new AmIAtBeginningOfThirdFloor(bot);
        amionsecondfloor = new AmIOnSecondFloor(bot);
    }


    @Override
    public boolean validate() {
        return floor3.contains(bot.getPlayer())||floor3End.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amionsecondfloor;
    }

    @Override
    public TreeTask successTask() {
        return amiatbeginningofthirdfloor;
    }
}
