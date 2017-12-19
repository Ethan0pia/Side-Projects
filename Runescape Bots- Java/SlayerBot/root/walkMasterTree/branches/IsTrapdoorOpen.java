package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.InteractGameObject;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsTrapdoorOpen extends BranchTask {

    private InteractGameObject interactgameobject;
    private InteractGameObject interactgameobject2;
    private OpiaSlayer bot;

    public IsTrapdoorOpen(OpiaSlayer bot){
        this.bot=bot;
        interactgameobject = new InteractGameObject(bot,"Trapdoor","Open");
        interactgameobject2 = new InteractGameObject(bot,"Trapdoor","Climb-down");
    }

    @Override
    public boolean validate() {
        GameObject trapdoor = GameObjects.newQuery().actions("Open").names("Trapdoor").within(new Area.Circular(bot.getPlayer().getPosition(),7)).results().nearest();
        return trapdoor!=null;
    }

    @Override
    public TreeTask failureTask() {
        return interactgameobject2;
    }

    @Override
    public TreeTask successTask() {
        return interactgameobject;
    }
}
