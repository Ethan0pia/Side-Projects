package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.InteractGameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class AmIThroughFirstDoorAnkous extends BranchTask {

    private AmIThroughSecondDoorAnkous amithroughseconddoorankous;
    private InteractGameObject interactgameobject;
    private Area firstArea = new Area.Rectangular(new Coordinate(2366,5218,0),new Coordinate(2356,5213,0));
    private OpiaSlayer bot;

    public AmIThroughFirstDoorAnkous(OpiaSlayer bot){
        this.bot=bot;
        interactgameobject = new InteractGameObject(bot,"Portal of Death","Open", new Area.Rectangular(new Coordinate(2366,5219,0),new Coordinate(2365,5218,0)));
        amithroughseconddoorankous = new AmIThroughSecondDoorAnkous(bot);
    }


    @Override
    public boolean validate() {
        return !firstArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return interactgameobject;
    }

    @Override
    public TreeTask successTask() {
        return amithroughseconddoorankous;
    }
}
