package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.SetNeedBank;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInventoryFull extends BranchTask {

    private SetNeedBank setNeedBank;
    private DoWeHaveFood doWeHaveFood;

    public IsInventoryFull(OpiaWineGrabber bot){
        setNeedBank = new SetNeedBank(bot);
        doWeHaveFood = new DoWeHaveFood(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("IsInventoryFull");
        return Inventory.isFull();
    }

    @Override
    public TreeTask failureTask() {
        return setNeedBank;
    }

    @Override
    public TreeTask successTask() {
        return doWeHaveFood;
    }
}
