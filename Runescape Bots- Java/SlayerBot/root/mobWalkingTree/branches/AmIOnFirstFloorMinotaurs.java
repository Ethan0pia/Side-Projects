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
public class AmIOnFirstFloorMinotaurs extends BranchTask {

    private AmIThroughFirstDoorMinotaurs amithroughfirstdoorminotaurs;
    private AmIAtStrongholdEntrance amibycaveentrance;
    private Area floor1 = new Area.Circular(new Coordinate(1859, 5243, 0), 50);
    private OpiaSlayer bot;

    public AmIOnFirstFloorMinotaurs(OpiaSlayer bot){
        this.bot=bot;
        amibycaveentrance = new AmIAtStrongholdEntrance(bot);
        amithroughfirstdoorminotaurs = new AmIThroughFirstDoorMinotaurs(bot);
    }


    @Override
    public boolean validate() {
        return floor1.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amibycaveentrance;
    }

    @Override
    public TreeTask successTask() {
        return amithroughfirstdoorminotaurs;
    }
}
