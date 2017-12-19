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
public class AmIThroughSecondDoorAnkous extends BranchTask {

    private AmIAtThirdDoorAnkous amiatthirddoorankous;
    private InteractGameObject interactgameobject;
    private Area secondArea = new Area.Rectangular(new Coordinate(2366,5221,0),new Coordinate(2356,5219,0));
    private OpiaSlayer bot;

    public AmIThroughSecondDoorAnkous(OpiaSlayer bot){
        this.bot=bot;
        interactgameobject = new InteractGameObject(bot,"Portal of Death","Open", new Area.Rectangular(new Coordinate(2366,5222,0),new Coordinate(2365,5221,0)));
        amiatthirddoorankous = new AmIAtThirdDoorAnkous(bot);
    }


    @Override
    public boolean validate() {
        return !secondArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return interactgameobject;
    }

    @Override
    public TreeTask successTask() {
        return amiatthirddoorankous;
    }
}
