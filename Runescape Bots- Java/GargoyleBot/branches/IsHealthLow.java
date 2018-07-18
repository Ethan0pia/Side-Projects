package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsHealthLow extends BranchTask {

    private DoesInventoryContainFood doesinventorycontainfood;
    private IsGearCorrect checkgear;

    private GargSlayer bot;

    public IsHealthLow(GargSlayer bot){
        this.bot=bot;
        doesinventorycontainfood = new DoesInventoryContainFood(bot);
        checkgear = new IsGearCorrect(bot);
    }

    @Override
    public boolean validate() {
        return Health.getCurrentPercent()<bot.getWhenToEat() && !bot.isBankBool();
    }

    @Override
    public TreeTask failureTask() {
        return checkgear;
    }

    @Override
    public TreeTask successTask() {
        return doesinventorycontainfood;
    }
}
