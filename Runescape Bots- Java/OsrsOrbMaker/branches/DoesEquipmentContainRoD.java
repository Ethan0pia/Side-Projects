package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WithdrawEquipRoD;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoesEquipmentContainRoD extends BranchTask {

    private HaveWeEmptiedInventory haveWeEmptiedInventory;
    private WithdrawEquipRoD withdrawEquipRoD;
    private OsrsOrbMaker bot;

    public DoesEquipmentContainRoD(OsrsOrbMaker bot){
        this.bot=bot;
        haveWeEmptiedInventory = new HaveWeEmptiedInventory(bot);
        withdrawEquipRoD = new WithdrawEquipRoD(bot);
    }

    @Override
    public boolean validate() {
        return Equipment.getItemIn(Equipment.Slot.RING)!=null;
    }

    @Override
    public TreeTask failureTask() {
        return withdrawEquipRoD;
    }

    @Override
    public TreeTask successTask() {
        return haveWeEmptiedInventory;
    }
}
