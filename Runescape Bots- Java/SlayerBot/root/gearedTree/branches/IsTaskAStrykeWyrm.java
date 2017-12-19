package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsTaskAStrykeWyrm extends BranchTask {

    private IsMobHPLow ismobhplow;
    private AmIStandingAtSafeSpot amIStandingAtSafeSpot;
    private OpiaSlayer bot;

    public IsTaskAStrykeWyrm(OpiaSlayer bot){
        this.bot=bot;
        amIStandingAtSafeSpot = new AmIStandingAtSafeSpot(bot);
        ismobhplow = new IsMobHPLow(bot);
    }

    @Override
    public boolean validate() {
        int mob = bot.getMonster().getIndex();
        return mob==103 || mob==104;
    }

    @Override
    public TreeTask failureTask() {
        return ismobhplow;
    }

    @Override
    public TreeTask successTask() {
        return amIStandingAtSafeSpot;
    }
}

