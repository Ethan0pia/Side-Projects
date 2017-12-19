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
public class AreWeBetweenBoulderAndGWDEntrance extends BranchTask {

    private AreWeAtTrollheim ispathtobouldernull;
    private IsThereASpecificGameObjectNearby isthereaspecificgameobjectnearby;
    private OpiaSlayer bot;
    private Area boulderAndEntrance=new Area.Rectangular(new Coordinate(2902,3712,0),new Coordinate(2930,3748,0));

    public AreWeBetweenBoulderAndGWDEntrance(OpiaSlayer bot){
        this.bot=bot;
        ispathtobouldernull = new AreWeAtTrollheim(bot);
        isthereaspecificgameobjectnearby = new IsThereASpecificGameObjectNearby(bot,"Hole","Climb-down",new Coordinate(2916, 3739, 0));
    }

    @Override
    public boolean validate() {
        return boulderAndEntrance.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return ispathtobouldernull;
    }

    @Override
    public TreeTask successTask() {
        return isthereaspecificgameobjectnearby;
    }
}
