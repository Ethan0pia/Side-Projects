package com.ethan0pia.bots.WineGrabberUltra.leveler.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
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
    private OpiaWineGrabberUltra bot;
    private Area cave = new Area.Circular(new Coordinate(2209,4376,0),30);

    public AreWeInsideCave(OpiaWineGrabberUltra bot){
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
