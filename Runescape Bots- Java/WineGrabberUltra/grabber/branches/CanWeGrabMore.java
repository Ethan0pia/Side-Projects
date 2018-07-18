package com.ethan0pia.bots.WineGrabberUltra.grabber.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class CanWeGrabMore extends BranchTask {

    private AreWeInTheTelegrabSpot areWeInTheTelegrabSpot;
    private IsInventoryFull isInventoryFull;
    private OpiaWineGrabberUltra bot;

    public CanWeGrabMore(OpiaWineGrabberUltra bot){
        this.bot=bot;
        isInventoryFull = new IsInventoryFull(bot);
        areWeInTheTelegrabSpot = new AreWeInTheTelegrabSpot(bot);
    }


    @Override
    public boolean validate() {
        Inventory.contains(bot.getFoodType());
        return Inventory.contains(bot.getFoodType()) && !Inventory.isFull() && Inventory.contains(563);
    }

    @Override
    public TreeTask failureTask() {
        return isInventoryFull;
    }

    @Override
    public TreeTask successTask() {
        return areWeInTheTelegrabSpot;
    }
}
