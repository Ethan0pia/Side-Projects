package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.InteractGameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIAtCaveEntrance extends BranchTask {

    private InteractGameObject interactgameobject;
    private AmIInsideCave amiinsidecave;
    private OpiaSlayer bot;

    public AmIAtCaveEntrance(OpiaSlayer bot){
        this.bot=bot;
        amiinsidecave = new AmIInsideCave(bot);
    }


    @Override
    public boolean validate() {
        interactgameobject = new InteractGameObject(bot,bot.getMonster().getObstacle(),bot.getMonster().getInteractionName());
        return GameObjects.newQuery().names(bot.getMonster().getObstacle()).actions(bot.getMonster().getInteractionName()).within(new Area.Circular(bot.getPlayer().getPosition(),8)).results().nearest()!=null;
    }

    @Override
    public TreeTask failureTask() {
        return amiinsidecave;
    }

    @Override
    public TreeTask successTask() {
        return interactgameobject;
    }
}
