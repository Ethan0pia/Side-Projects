package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkCaveEntrance;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.InteractGameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AmIAtStrongholdEntrance extends BranchTask {

    private WalkCaveEntrance walkCaveEntrance;
    private InteractGameObject interactGameObject;
    Area webArea = new Area.Circular(new Coordinate(3081, 3421, 0), 7);
    private OpiaSlayer bot;

    public AmIAtStrongholdEntrance(OpiaSlayer bot){
        this.bot=bot;
        walkCaveEntrance = new WalkCaveEntrance(bot);
        interactGameObject = new InteractGameObject(bot,"Entrance", "Climb-down");
    }


    @Override
    public boolean validate() {
        return webArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkCaveEntrance;
    }

    @Override
    public TreeTask successTask() {
        return interactGameObject;
    }
}
