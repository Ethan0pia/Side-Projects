package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoWeNeedToBank extends BranchTask {

    private IsHealthLow isHealthLow;
    private AreWeInChurch areWeInChurch;
    private OpiaWineGrabber bot;

    public DoWeNeedToBank(OpiaWineGrabber bot){
        this.bot=bot;
        isHealthLow = new IsHealthLow(bot);
        areWeInChurch = new AreWeInChurch(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("DoWeNeedToBank");
        if(!bot.isUseCam() && (bot.getCameraPitch()==1 || bot.getCameraYaw()==400)){
            bot.setCameraPitch(Camera.getPitch());
            bot.setCameraYaw(Camera.getYaw());
        }

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
