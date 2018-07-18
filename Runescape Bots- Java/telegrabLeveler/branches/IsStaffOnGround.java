package com.ethan0pia.bots.TelegrabLeveler.branches;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.TelegrabLeveler.leaves.AttackTroll;
import com.ethan0pia.bots.TelegrabLeveler.leaves.LootStaff;
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
    private TelegrabLeveler bot;

    public IsStaffOnGround(TelegrabLeveler bot){
        this.bot=bot;
        lootstaff = new LootStaff(bot);
        attacktrollshaman = new AttackTroll(bot, "Troll shaman");
    }


    @Override
    public boolean validate() {
        return !GroundItems.newQuery().names("Staff of air").within(new Area.Circular(bot.getPlayer().getPosition(),14)).results().isEmpty();
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
