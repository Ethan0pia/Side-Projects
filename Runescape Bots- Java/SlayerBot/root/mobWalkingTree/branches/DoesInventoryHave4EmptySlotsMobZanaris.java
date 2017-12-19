package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WithdrawDramenStaff;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class DoesInventoryHave4EmptySlotsMobZanaris extends BranchTask {

    private WithdrawDramenStaff withdrawdramenstaff;
    private DoesInventoryContainFoodWalkMob doesinventorycontainfood;

    public DoesInventoryHave4EmptySlotsMobZanaris(OpiaSlayer bot){
        withdrawdramenstaff = new WithdrawDramenStaff(bot);
        doesinventorycontainfood = new DoesInventoryContainFoodWalkMob(bot);
    }


    @Override
    public boolean validate() {
        return Inventory.getEmptySlots()>3;
    }

    @Override
    public TreeTask failureTask() {
        return doesinventorycontainfood;
    }

    @Override
    public TreeTask successTask() {
        return withdrawdramenstaff;
    }
}
