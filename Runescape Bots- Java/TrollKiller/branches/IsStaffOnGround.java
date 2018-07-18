package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.ethan0pia.bots.TrollKiller.leaves.AttackTroll;
import com.ethan0pia.bots.TrollKiller.leaves.LootStaff;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsStaffOnGround extends BranchTask {

    private LootStaff lootstaff;
    private AttackTroll attacktrollshaman;
    private TrollKiller bot;

    public IsStaffOnGround(TrollKiller bot){
        this.bot=bot;
        lootstaff = new LootStaff(bot);
        attacktrollshaman = new AttackTroll(bot, "Troll shaman");
    }


    @Override
    public boolean validate() {
        return !Inventory.contains(1381) && !GroundItems.newQuery().names("Staff of air").within(new Area.Circular(bot.getPlayer().getPosition(),14)).results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return attacktrollshaman;
    }

    @Override
    public TreeTask successTask() {
        return lootstaff;
    }
}
