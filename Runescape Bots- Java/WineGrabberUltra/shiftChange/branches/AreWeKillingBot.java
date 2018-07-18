package com.ethan0pia.bots.WineGrabberUltra.shiftChange.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.shiftChange.leaves.ChangeShift;
import com.ethan0pia.bots.WineGrabberUltra.shiftChange.leaves.KillBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class AreWeKillingBot extends BranchTask {

    private  KillBot killBot;
    private ChangeShift changeShift;
    private OpiaWineGrabberUltra bot;

    public AreWeKillingBot(OpiaWineGrabberUltra bot){
        this.bot=bot;
        killBot = new KillBot(bot);
        changeShift = new ChangeShift(bot);
    }

    @Override
    public boolean validate() {
        return bot.isKill();
    }

    @Override
    public TreeTask failureTask() {
        return changeShift;
    }

    @Override
    public TreeTask successTask() {
        return killBot;
    }
}
