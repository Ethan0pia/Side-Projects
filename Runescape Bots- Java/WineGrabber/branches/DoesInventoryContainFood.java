package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.EatFood;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesInventoryContainFood extends BranchTask {

    private EatFood eatfood;
    private StillAtZammyArea stillatzammyarea;
    private OpiaWineGrabber bot;

    public DoesInventoryContainFood(OpiaWineGrabber bot){
        this.bot=bot;
        eatfood = new EatFood(bot);
        stillatzammyarea = new StillAtZammyArea(bot);
    }


    @Override
    public boolean validate() {
        return !bot.isBankBool();
    }

    @Override
    public TreeTask failureTask() {
        return stillatzammyarea;
    }

    @Override
    public TreeTask successTask() {
        return eatfood;
    }
}
