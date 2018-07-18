package com.ethan0pia.bots.WineGrabberUltra.grabber.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoWeNeedToBank extends BranchTask {

    private IsHealthLow isHealthLow;
    private AreWeInChurch areWeInChurch;
    private OpiaWineGrabberUltra bot;

    public DoWeNeedToBank(OpiaWineGrabberUltra bot){
        this.bot=bot;
        isHealthLow = new IsHealthLow(bot);
        areWeInChurch = new AreWeInChurch(bot);
    }


    @Override
    public boolean validate() {
        return bot.isBankBool();
    }

    @Override
    public TreeTask failureTask() {
        return isHealthLow;
    }

    @Override
    public TreeTask successTask() {
        return areWeInChurch;
    }
}
