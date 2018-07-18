package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.ethan0pia.bots.TrollKiller.leaves.EquipItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsBowEquipped extends BranchTask {

    private EquipItem equipItem;
    private IsStaffOnGround isStaffOnGround;
    private TrollKiller bot;

    public IsBowEquipped(TrollKiller bot){
        this.bot=bot;
        isStaffOnGround = new IsStaffOnGround(bot);
        equipItem = new EquipItem(bot,19830);
    }


    @Override
    public boolean validate() {
        return !Equipment.contains(19830);
    }

    @Override
    public TreeTask failureTask() {
        return isStaffOnGround;
    }

    @Override
    public TreeTask successTask() {
        return equipItem;
    }
}
