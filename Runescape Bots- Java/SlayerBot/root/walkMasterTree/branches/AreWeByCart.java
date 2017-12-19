package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.InteractGameObject;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkCart;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeByCart extends BranchTask {

    private InteractGameObject interactGameObject;
    private WalkCart walkCart;
    private OpiaSlayer bot;
    private Area cartArea = new Area.Circular(new Coordinate(2776,3212,0),8);

    public AreWeByCart(OpiaSlayer bot){
        this.bot=bot;
        walkCart = new WalkCart(bot);
        interactGameObject = new InteractGameObject(bot,"Travel cart","Pay-fare");
    }


    @Override
    public boolean validate() {
        return cartArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkCart;
    }

    @Override
    public TreeTask successTask() {
        return interactGameObject;
    }
}
