package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.EmptyLeaf;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAdrenalineAt50 extends BranchTask {

    private IsThresholdReady isThresholdReady;
    private EmptyLeaf empty;

    private OpiaSpiritualMages bot;

    public IsAdrenalineAt50(OpiaSpiritualMages bot){
        this.bot=bot;
        empty = new EmptyLeaf(bot);
        isThresholdReady = new IsThresholdReady(bot);
    }

    @Override
    public boolean validate() {
        return Powers.getAdrenalinePercentage()>49;
    }

    @Override
    public TreeTask failureTask() {
        return empty;
    }

    @Override
    public TreeTask successTask() {
        return isThresholdReady;
    }
}
