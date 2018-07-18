package com.ethan0pia.bots.WineGrabberUltra;

import com.ethan0pia.bots.WineGrabberUltra.shiftChange.branches.DoWeHaveNotedWine;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class IsShiftOver extends BranchTask {

    private IsFaladorLodeActivated isFaladorLodeActivated;
    private DoWeHaveNotedWine doWeHaveNotedWine;
    private OpiaWineGrabberUltra bot;

    public IsShiftOver(OpiaWineGrabberUltra bot){
        this.bot=bot;
        isFaladorLodeActivated = new IsFaladorLodeActivated(bot);
        doWeHaveNotedWine = new DoWeHaveNotedWine(bot);
    }

    @Override
    public boolean validate() {
        if(bot.getOriginalWorld()==0){
            bot.setOriginalWorld(Worlds.getCurrent());
        }
        return bot.getShiftWatch() + 0.2 > bot.getShiftLength() || bot.isForceShiftEnd() || bot.isKill();
    }

    @Override
    public TreeTask failureTask() {
        return isFaladorLodeActivated;
    }

    @Override
    public TreeTask successTask() {
        return doWeHaveNotedWine;
    }
}
