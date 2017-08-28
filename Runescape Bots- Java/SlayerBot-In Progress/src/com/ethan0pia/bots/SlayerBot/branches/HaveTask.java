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

    public HaveTask(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private IsGeared isgeared = new IsGeared(Bot);
    private AtMaster atmaster = new AtMaster(Bot);

    @Override
    public boolean validate() {
        if(Varbits.load(7917).getValue()==0){
            return false;
        }
        else{
            return true;
        }
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
