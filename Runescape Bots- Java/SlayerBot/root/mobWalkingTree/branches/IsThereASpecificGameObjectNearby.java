package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkToCoords;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.InteractGameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsThereASpecificGameObjectNearby extends BranchTask {

    private InteractGameObject interactgameobject;
    private WalkToCoords walktocoords;
    private OpiaSlayer bot;
    private Area objectArea;

    public IsThereASpecificGameObjectNearby(OpiaSlayer bot, String obstacle, String interaction, Coordinate toWalkTo){
        this.bot=bot;
        interactgameobject = new InteractGameObject(bot,obstacle,interaction);
        walktocoords = new WalkToCoords(bot,toWalkTo);
        objectArea = new Area.Circular(toWalkTo,5);
    }

    @Override
    public boolean validate() {
        return objectArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walktocoords;
    }

    @Override
    public TreeTask successTask() {
        return interactgameobject;
    }
}
