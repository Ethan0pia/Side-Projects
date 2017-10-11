package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * Checks player's Health, true if under X%
 */
public class CheckHealth extends BranchTask {

    private DoesBagHaveFood doesbaghavefood;
    private IsAbilityBarSet isAbilityBarSet;
    private OpiaSpiritualMages bot;

    public CheckHealth(OpiaSpiritualMages bot){
        this.bot=bot;
        doesbaghavefood = new DoesBagHaveFood(bot);
        isAbilityBarSet = new IsAbilityBarSet(bot);
    }

    @Override
    public boolean validate() {
        return !bot.isGetFood() && Health.getCurrentPercent()< bot.getWhenToEat();
    }

    @Override
    public TreeTask failureTask() {
        return isAbilityBarSet;
    }

    @Override
    public TreeTask successTask() {
        return doesbaghavefood;
    }
}
