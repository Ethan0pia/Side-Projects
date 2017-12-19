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
public class AmIThroughThirdDoorMinotaurs extends BranchTask {

    private InteractGameObject interactgameobject;
    private AmIByThirdDoorMinotaurs amIbyThirdDoor;
    private Area thirdArea = new Area.Rectangular(new Coordinate(1858,5235,0),new Coordinate(1866,5212,0));
    private OpiaSlayer bot;

    public AmIThroughThirdDoorMinotaurs(OpiaSlayer bot){
        this.bot=bot;
        amIbyThirdDoor = new AmIByThirdDoorMinotaurs(bot);
        interactgameobject = new InteractGameObject(bot,"Gate of War","Open", new Area.Rectangular(new Coordinate(1869,5218,0),new Coordinate(1870,5217,0)));
    }


    @Override
    public boolean validate() {
        return !thirdArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amIbyThirdDoor;
    }

    @Override
    public TreeTask successTask() {
        return interactgameobject;
    }
}
