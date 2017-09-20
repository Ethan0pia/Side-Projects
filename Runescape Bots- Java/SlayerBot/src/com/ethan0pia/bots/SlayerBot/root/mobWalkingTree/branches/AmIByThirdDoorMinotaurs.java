package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkToThirdDoorMinotaurs;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.InteractGameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class AmIByThirdDoorMinotaurs extends BranchTask {

    private InteractGameObject interactgameobject;
    private WalkToThirdDoorMinotaurs amithroughthirddoorminotaurs;
    private Area thirdArea = new Area.Circular(new Coordinate(1865,5217,0),3);
    private OpiaSlayer bot;

    public AmIByThirdDoorMinotaurs(OpiaSlayer bot){
        this.bot=bot;
        interactgameobject = new InteractGameObject(bot,"Gate of War","Open", new Area.Rectangular(new Coordinate(1866,5218,0),new Coordinate(1867,5217,0)));
        amithroughthirddoorminotaurs = new WalkToThirdDoorMinotaurs(bot);
    }


    @Override
    public boolean validate() {
        return thirdArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amithroughthirddoorminotaurs;
    }

    @Override
    public TreeTask successTask() {
        return interactgameobject;
    }
}
