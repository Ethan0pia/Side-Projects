package com.ethan0pia.bots.TelegrabLeveler.branches;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeInsideCave extends BranchTask {

    private DoWeHaveAirStaffEquipped dowehaveairstaffequipped;
    private DoWeHaveStaffOrSword dowehavestafforsword;
    private TelegrabLeveler bot;
    private Area cave = new Area.Circular(new Coordinate(2209,4376,0),30);

    public AreWeInsideCave(TelegrabLeveler bot){
        this.bot=bot;
        dowehavestafforsword = new DoWeHaveStaffOrSword(bot);
        dowehaveairstaffequipped = new DoWeHaveAirStaffEquipped(bot);
    }


    @Override
    public boolean validate() {
        return cave.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return dowehavestafforsword;
    }

    @Override
    public TreeTask successTask() {
        return dowehaveairstaffequipped;
    }
}
