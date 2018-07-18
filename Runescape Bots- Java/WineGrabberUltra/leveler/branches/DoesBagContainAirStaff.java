package com.ethan0pia.bots.WineGrabberUltra.leveler.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.EquipStaff;
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
    private OpiaWineGrabberUltra bot;

    public DoesBagContainAirStaff(OpiaWineGrabberUltra bot){
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
