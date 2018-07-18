package com.ethan0pia.bots.WineGrabberUltra.leveler.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class DoWeHaveAirStaffEquipped extends BranchTask {

    private AreWeInWestRoom areweinwestroom;
    private DoesBagContainAirStaff doesbagcontainairstaff;
    private OpiaWineGrabberUltra bot;

    public DoWeHaveAirStaffEquipped(OpiaWineGrabberUltra bot){
        this.bot=bot;
        doesbagcontainairstaff = new DoesBagContainAirStaff(bot);
        areweinwestroom = new AreWeInWestRoom(bot);
    }


    @Override
    public boolean validate() {
        return Equipment.contains(1381);
    }

    @Override
    public TreeTask failureTask() {
        return doesbagcontainairstaff;
    }

    @Override
    public TreeTask successTask() {
        return areweinwestroom;
    }
}
