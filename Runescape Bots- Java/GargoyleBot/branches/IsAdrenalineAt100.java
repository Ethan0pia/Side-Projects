package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsAdrenalineAt100 extends BranchTask {

    private IsUltimateReady isultimateready;
    private IsAdrenalineOver50 isadrenalineover50;

    private GargSlayer bot;

    public IsAdrenalineAt100(GargSlayer bot){
        this.bot=bot;
        isultimateready = new IsUltimateReady(bot);
        isadrenalineover50 = new IsAdrenalineOver50(bot);
    }

    @Override
    public boolean validate() {
        return Powers.getAdrenalinePercentage()>=100;
    }

    @Override
    public TreeTask failureTask() {
        return isadrenalineover50;
    }

    @Override
    public TreeTask successTask() {
        return isultimateready;
    }
}
