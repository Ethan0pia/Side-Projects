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
public class AreWeInGWD extends BranchTask {

    private AreWeBetweenBoulderAndGWDEntrance ispathtocaveentrancenull;
    private IsThereASpecificGameObjectNearby isthereaspecificgameobjectnearby;
    OpiaSlayer bot;
    private Area GWD=new Area.Circular(new Coordinate(2881,5312,0),150);

    public AreWeInGWD(OpiaSlayer bot){
        this.bot=bot;
        ispathtocaveentrancenull = new AreWeBetweenBoulderAndGWDEntrance(bot);
        isthereaspecificgameobjectnearby = new IsThereASpecificGameObjectNearby(bot,"Broken bridge","Jump-over",new Coordinate(2887, 5336, 0));
    }

    @Override
    public boolean validate() {
        return GWD.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return ispathtocaveentrancenull;
    }

    @Override
    public TreeTask successTask() {
        return isthereaspecificgameobjectnearby;
    }
}
