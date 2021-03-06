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
public class AmIAtBeginningSecondFloor extends BranchTask {

    private InteractGameObject interactgameobject;
    private InteractGameObject interactgameobject2;
    private Area floor2 = new Area.Circular(new Coordinate(2042, 5245, 0), 15);
    private OpiaSlayer bot;

    public AmIAtBeginningSecondFloor(OpiaSlayer bot){
        this.bot=bot;
        interactgameobject = new InteractGameObject(bot,"Ladder", "Climb-down");
        interactgameobject2 = new InteractGameObject(bot,"Portal","Enter");
    }


    @Override
    public boolean validate() {
        return floor2.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return interactgameobject;
    }

    @Override
    public TreeTask successTask() {
        return interactgameobject2;
    }
}
