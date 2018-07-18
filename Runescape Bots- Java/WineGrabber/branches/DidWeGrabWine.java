package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.RunSafeSpot;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DidWeGrabWine extends BranchTask {

    private RunSafeSpot runsafespot;
    private AreWeInTheTelegrabSpot areweinthetelegrabspot;

    private OpiaWineGrabber bot;

    public DidWeGrabWine(OpiaWineGrabber bot){
        this.bot=bot;
        areweinthetelegrabspot = new AreWeInTheTelegrabSpot(bot);
        runsafespot = new RunSafeSpot(bot);
    }

    @Override
    public boolean validate() {
        Environment.getLogger().debug("DidWeGrabWine");
        return bot.isRun();
    }

    @Override
    public TreeTask failureTask() {
        return areweinthetelegrabspot;
    }

    @Override
    public TreeTask successTask() {
        return runsafespot;
    }
}
