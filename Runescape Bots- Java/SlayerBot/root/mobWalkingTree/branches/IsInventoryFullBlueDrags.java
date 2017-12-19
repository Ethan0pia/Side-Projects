package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WithdrawKey;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsInventoryFullBlueDrags extends BranchTask {

    private DoesInventoryContainFoodWalkMob doesinventorycontainfoodbluedrags;
    private WithdrawKey withdrawkey;

    public IsInventoryFullBlueDrags(OpiaSlayer bot){
        withdrawkey = new WithdrawKey(bot);
        doesinventorycontainfoodbluedrags = new DoesInventoryContainFoodWalkMob(bot);
    }


    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public TreeTask failureTask() {
        return withdrawkey;
    }

    @Override
    public TreeTask successTask() {
        return doesinventorycontainfoodbluedrags;
    }
}
