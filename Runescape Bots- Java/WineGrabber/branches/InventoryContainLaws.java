package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class InventoryContainLaws extends BranchTask {

    private StillAtZammyArea stillAtZammyArea;
    private DoesInventoryContainFood doesInventoryContainFood;

    public InventoryContainLaws(OpiaWineGrabber bot){
        stillAtZammyArea = new StillAtZammyArea(bot);
        doesInventoryContainFood = new DoesInventoryContainFood(bot);
    }


    @Override
    public boolean validate() {
        return Inventory.contains(563);
    }

    @Override
    public TreeTask failureTask() {
        return stillAtZammyArea;
    }

    @Override
    public TreeTask successTask() {
        return doesInventoryContainFood;
    }
}
