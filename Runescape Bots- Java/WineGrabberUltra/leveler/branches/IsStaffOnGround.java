package com.ethan0pia.bots.WineGrabberUltra.leveler.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.AttackTroll;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.LootStaff;
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
    private OpiaWineGrabberUltra bot;

    public IsStaffOnGround(OpiaWineGrabberUltra bot){
        this.bot=bot;
        lootstaff = new LootStaff(bot);
        attacktrollshaman = new AttackTroll(bot, "Troll shaman", false);
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
