package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * Checks player's Health, true if under X%
 */
public class CheckHealth extends BranchTask {

    private DoesBagHaveFood doesbaghavefood;
    private DoIHaveATask doihaveatask;
    private OpiaSlayer bot;

    public CheckHealth(OpiaSlayer bot){
        this.bot=bot;
        doesbaghavefood = new DoesBagHaveFood(bot);
        doihaveatask = new DoIHaveATask(bot);
    }

    @Override
    public boolean validate() {
        return !bot.isGetFood() && Health.getCurrentPercent()< bot.getWhenToEat();
    }

    @Override
    public TreeTask failureTask() {
        return doihaveatask;
    }

    @Override
    public TreeTask successTask() {
        return doesbaghavefood;
    }
}
