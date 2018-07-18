package com.ethan0pia.bots.WineGrabberUltra.grabber.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.EatFood;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class DoWeHaveFood extends BranchTask {

    private EmptyLeaf emptyLeaf;
    private EatFood eatFood;

    private OpiaWineGrabberUltra bot;

    public DoWeHaveFood(OpiaWineGrabberUltra bot){
        this.bot=bot;
        emptyLeaf = new EmptyLeaf(bot);
        eatFood = new EatFood(bot);
    }


    @Override
    public boolean validate() {
        Inventory.contains(bot.getFoodType());
        return Inventory.contains(bot.getFoodType());
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
        return eatFood;
    }
}
