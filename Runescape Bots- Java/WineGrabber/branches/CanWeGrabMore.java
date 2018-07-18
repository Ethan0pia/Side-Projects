package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class CanWeGrabMore extends BranchTask {

    private DidWeGrabWine didWeGrabWine;
    private IsInventoryFull isInventoryFull;
    private OpiaWineGrabber bot;

    public CanWeGrabMore(OpiaWineGrabber bot){
        this.bot=bot;
        isInventoryFull = new IsInventoryFull(bot);
        didWeGrabWine = new DidWeGrabWine(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("CanWeGrabMore");
        Inventory.contains(bot.getFoodType());
        return Inventory.contains(bot.getFoodType()) && !Inventory.isFull() && Inventory.contains(563);
    }

    @Override
    public TreeTask failureTask() {
        return isInventoryFull;
    }

    @Override
    public TreeTask successTask() {
        return didWeGrabWine;
    }
}
