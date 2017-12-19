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
public class AmIOnFourthFloorAnkous extends BranchTask {

    private AmIThroughFirstDoorAnkous amithroughfirstdoorankous;
    private AmIOnTheThirdFloorAnkous amionthethirdfloorankous;
    private Area floor4 = new Area.Circular(new Coordinate(2356, 5213, 0), 150);
    private OpiaSlayer bot;

    public AmIOnFourthFloorAnkous(OpiaSlayer bot){
        this.bot=bot;
        amionthethirdfloorankous = new AmIOnTheThirdFloorAnkous(bot);
        amithroughfirstdoorankous = new AmIThroughFirstDoorAnkous(bot);
    }


    @Override
    public boolean validate() {
        return floor4.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amionthethirdfloorankous;
    }

    @Override
    public TreeTask successTask() {
        return amithroughfirstdoorankous;
    }
}
