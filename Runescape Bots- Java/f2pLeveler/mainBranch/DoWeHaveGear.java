package com.ethan0pia.bots.f2pLeveler.mainBranch;

import com.ethan0pia.bots.f2pLeveler.StopBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoWeHaveGear extends BranchTask {

    private StopBot stopBot = new StopBot();
    private IsDefRange50 isDefRangeAt50 = new IsDefRange50();

    @Override
    public boolean validate() {
        return Equipment.getItemIn(Equipment.Slot.CAPE)!=null && Equipment.getItemIn(Equipment.Slot.NECK)!=null && Equipment.getItemIn(Equipment.Slot.FEET)!=null && Equipment.getItemIn(Equipment.Slot.LEGS)!=null && Equipment.getItemIn(Equipment.Slot.BODY)!=null &&
                Equipment.getItemIn(Equipment.Slot.HAND)!=null && Equipment.getItemIn(Equipment.Slot.HEAD)!=null && Equipment.getItemIn(Equipment.Slot.WEAPON)!=null && Equipment.getItemIn(Equipment.Slot.AMMUNITION)!=null;
    }

    @Override
    public TreeTask failureTask() {
        return stopBot;
    }

    @Override
    public TreeTask successTask() {
        return isDefRangeAt50;
    }
}
