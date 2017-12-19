package com.ethan0pia.bots.telegrabLeveler.branches;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
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
    private TelegrabLeveler bot;

    public DoWeHaveAirStaffEquipped(TelegrabLeveler bot){
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
