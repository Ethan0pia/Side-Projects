package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.EatFood;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsHealthLow extends BranchTask {

    private EatFood eatFood = new EatFood();
    private AmIAtShadowWiz amiatshadowwiz = new AmIAtShadowWiz();

    @Override
    public boolean validate() {
        return Health.getCurrentPercent()<50;
    }

    @Override
    public TreeTask failureTask() {
        return amiatshadowwiz;
    }

    @Override
    public TreeTask successTask() {
        return eatFood;
    }
}
