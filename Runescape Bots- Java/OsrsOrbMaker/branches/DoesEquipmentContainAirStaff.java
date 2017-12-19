package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WithdrawEquipStaff;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesEquipmentContainAirStaff extends BranchTask {

    private DoesEquipmentContainRoD doesEquipmentContainRoD;
    private WithdrawEquipStaff withdrawequipstaff;
    private OsrsOrbMaker bot;

    public DoesEquipmentContainAirStaff(OsrsOrbMaker bot){
        this.bot=bot;
        doesEquipmentContainRoD = new DoesEquipmentContainRoD(bot);
        withdrawequipstaff = new WithdrawEquipStaff(bot);
    }

    @Override
    public boolean validate() {
        return Equipment.contains(1381);
    }

    @Override
    public TreeTask failureTask() {
        return withdrawequipstaff;
    }

    @Override
    public TreeTask successTask() {
        return doesEquipmentContainRoD;
    }
}
