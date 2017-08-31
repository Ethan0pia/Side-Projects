package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class HaveTask extends BranchTask {

    private GoodAssSlayerBot Bot;

    private IsGeared isgeared;
    private AtMaster atmaster;

    public HaveTask(GoodAssSlayerBot bot){
        this.Bot=bot;
        isgeared = new IsGeared(bot);
        atmaster = new AtMaster(bot);

    }


    @Override
    public boolean validate() {
        return Varbits.load(7917).getValue()!=0;
    }

    @Override
    public TreeTask failureTask() {
        return atmaster;
    }

    @Override
    public TreeTask successTask() {
        return isgeared;
    }
}
