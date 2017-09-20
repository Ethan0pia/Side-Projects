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
public class AmIThroughThirdDoorAnkous extends BranchTask {

    private InteractGameObject interactgameobject;
    private InteractGameObject interactGameObject2;
    private Area thirdArea = new Area.Rectangular(new Coordinate(2366,5231,0),new Coordinate(2356,5222,0));
    private OpiaSlayer bot;

    public AmIThroughThirdDoorAnkous(OpiaSlayer bot){
        this.bot=bot;
        interactgameobject = new InteractGameObject(bot,"Portal of Death","Open", new Area.Rectangular(new Coordinate(2360,5232,0),new Coordinate(2359,5231,0)));
        interactGameObject2 = new InteractGameObject(bot,"Portal of Death","Open", new Area.Rectangular(new Coordinate(2360,5235,0),new Coordinate(2359,5234,0)));
    }


    @Override
    public boolean validate() {
        return thirdArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return interactGameObject2;
    }

    @Override
    public TreeTask successTask() {
        return interactgameobject;
    }
}
