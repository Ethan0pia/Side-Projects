package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesEquipmentContainChargedGlory extends BranchTask {

    private DoesEquipmentContainAirStaff doesEquipmentContainAirStaff;
    private DoesBankContainChargedGlories doesbankcontainchargedglories;
    private OsrsOrbMaker bot;

    public DoesEquipmentContainChargedGlory(OsrsOrbMaker bot){
        this.bot=bot;
        doesEquipmentContainAirStaff = new DoesEquipmentContainAirStaff(bot);
        doesbankcontainchargedglories = new DoesBankContainChargedGlories(bot);
    }

    @Override
    public boolean validate() {
        return Equipment.contains(1706) || Equipment.contains(1708) || Equipment.contains(1710) || Equipment.contains(1712) || Equipment.contains(11976) || Equipment.contains(11978);
    }

    @Override
    public TreeTask failureTask() {
        return doesbankcontainchargedglories;
    }

    @Override
    public TreeTask successTask() {
        return doesEquipmentContainAirStaff;
    }
}
