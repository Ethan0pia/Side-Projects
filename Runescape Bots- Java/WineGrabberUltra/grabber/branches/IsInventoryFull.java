package com.ethan0pia.bots.WineGrabberUltra.grabber.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInventoryFull extends BranchTask {

    private EmptyLeaf emptyLeaf;
    private DoWeHaveFood doWeHaveFood;
    private OpiaWineGrabberUltra bot;

    public IsInventoryFull(OpiaWineGrabberUltra bot){
        emptyLeaf = new EmptyLeaf(bot);
        doWeHaveFood = new DoWeHaveFood(bot);
        this.bot=bot;
    }


    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public TreeTask failureTask() {
        Environment.getLogger().debug("SetNeedBank");
        bot.setCurrentTask("We need to bank.");
        bot.setBankBool(true);
        return emptyLeaf;
    }

    @Override
    public TreeTask successTask() {
        return doWeHaveFood;
    }
}
