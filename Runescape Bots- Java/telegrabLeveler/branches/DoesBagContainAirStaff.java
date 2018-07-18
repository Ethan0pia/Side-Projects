package com.ethan0pia.bots.TelegrabLeveler.branches;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.TelegrabLeveler.leaves.EquipStaff;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class DoesBagContainAirStaff extends BranchTask {

    private EquipStaff equipstaff;
    private AreWeInEastRoom areweineastroom;
    private TelegrabLeveler bot;

    public DoesBagContainAirStaff(TelegrabLeveler bot){
        this.bot=bot;
        areweineastroom = new AreWeInEastRoom(bot);
        equipstaff = new EquipStaff(bot);
    }


    @Override
    public boolean validate() {
        return Inventory.contains(1381);
    }

    @Override
    public TreeTask failureTask() {
        return areweineastroom;
    }

    @Override
    public TreeTask successTask() {
        return equipstaff;
    }
}
