package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * Checks player's Health, true if under X%
 */
public class IsHealthLow extends BranchTask {

    private DoesBagHaveFood doesbaghavefood;
    private IsAbilityBarSet isAbilityBarSet;
    private OpiaSpiritualMages bot;

    public IsHealthLow(OpiaSpiritualMages bot){
        this.bot=bot;
        doesbaghavefood = new DoesBagHaveFood(bot);
        isAbilityBarSet = new IsAbilityBarSet(bot);
    }

    @Override
    public boolean validate() {
        return !bot.isNeedBank() && Health.getCurrentPercent()< bot.getWhenToEat();
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
