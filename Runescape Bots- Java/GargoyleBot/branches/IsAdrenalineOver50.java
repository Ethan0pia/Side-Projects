package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.EmptyLeaf;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsAdrenalineOver50 extends BranchTask {

    private IsThresholdReady isthresholdready;
    private EmptyLeaf emptyleaf;

    private GargSlayer bot;

    public IsAdrenalineOver50(GargSlayer bot){
        this.bot=bot;
        isthresholdready = new IsThresholdReady(bot);
        emptyleaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        return Powers.getAdrenalinePercentage()>50;
    }

    @Override
    public TreeTask failureTask() {
        return emptyleaf;
    }

    @Override
    public TreeTask successTask() {
        return isthresholdready;
    }
}
