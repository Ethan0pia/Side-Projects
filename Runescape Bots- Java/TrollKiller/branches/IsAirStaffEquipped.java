package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.ethan0pia.bots.TrollKiller.leaves.AttackTroll;
import com.ethan0pia.bots.TrollKiller.leaves.EquipItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsAirStaffEquipped extends BranchTask {

    private AttackTroll attackTroll;
    private EquipItem equipItem;
    private TrollKiller bot;

    public IsAirStaffEquipped(TrollKiller bot){
        this.bot=bot;
        equipItem = new EquipItem(bot,1381);
        attackTroll = new AttackTroll(bot, "Troll brute");
    }


    @Override
    public boolean validate() {
        return Equipment.contains(1381);
    }

    @Override
    public TreeTask failureTask() {
        return equipItem;
    }

    @Override
    public TreeTask successTask() {
        return attackTroll;
    }
}
