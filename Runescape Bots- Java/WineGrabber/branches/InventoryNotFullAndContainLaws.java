package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class InventoryNotFullAndContainLaws extends BranchTask {

    private DidWeGrabWine didWeGrabWine;
    private InventoryContainLaws inventoryContainLaws;
    private OpiaWineGrabber bot;

    public InventoryNotFullAndContainLaws(OpiaWineGrabber bot){
        this.bot=bot;
        inventoryContainLaws = new InventoryContainLaws(bot);
        didWeGrabWine = new DidWeGrabWine(bot);
    }


    @Override
    public boolean validate() {
        return !Inventory.isFull() && Inventory.contains(563) && !bot.isBankBool() && !Inventory.newQuery().actions("Eat").results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return inventoryContainLaws;
    }

    @Override
    public TreeTask successTask() {
        return didWeGrabWine;
    }
}
