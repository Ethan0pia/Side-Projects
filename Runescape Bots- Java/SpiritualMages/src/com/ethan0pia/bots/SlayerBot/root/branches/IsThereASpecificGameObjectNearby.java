package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.WalkToCoords;
import com.ethan0pia.bots.SlayerBot.root.leaves.InteractGameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsThereASpecificGameObjectNearby extends BranchTask {

    private InteractGameObject interactgameobject;
    private WalkToCoords walktocoords;
    private OpiaSpiritualMages bot;
    private Area objectArea;

    public IsThereASpecificGameObjectNearby(OpiaSpiritualMages bot, String obstacle, String interaction, Coordinate toWalkTo){
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
