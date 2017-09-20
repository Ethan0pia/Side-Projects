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
public class AmIThroughSecondDoorMinotaurs extends BranchTask {

    private AmIThroughThirdDoorMinotaurs amIAtThirdDoorMinotaurs;
    private InteractGameObject interactgameobject;
    private Area secondArea = new Area.Rectangular(new Coordinate(1858,5238,0),new Coordinate(1859,5236,0));
    private OpiaSlayer bot;

    public AmIThroughSecondDoorMinotaurs(OpiaSlayer bot){
        this.bot=bot;
        interactgameobject = new InteractGameObject(bot,"Gate of War","Open", new Area.Rectangular(new Coordinate(1858,5236,0),new Coordinate(1859,5235,0)));
        amIAtThirdDoorMinotaurs = new AmIThroughThirdDoorMinotaurs(bot);
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
        return amIAtThirdDoorMinotaurs;
    }
}
