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
public class AmIThroughFirstDoorMinotaurs extends BranchTask {

    private AmIThroughSecondDoorMinotaurs amithroughseconddoorminotaurs;
    private InteractGameObject interactgameobject;
    private  Area firstArea = new Area.Rectangular(new Coordinate(1861,5244,0),new Coordinate(1850,5239,0));
    private OpiaSlayer bot;

    public AmIThroughFirstDoorMinotaurs(OpiaSlayer bot){
        this.bot=bot;
        amithroughseconddoorminotaurs = new AmIThroughSecondDoorMinotaurs(bot);
        interactgameobject = new InteractGameObject(bot,"Gate of War","Open", new Area.Rectangular(new Coordinate(1858,5239,0),new Coordinate(1859,5238,0)));
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
        return amithroughseconddoorminotaurs;
    }
}
