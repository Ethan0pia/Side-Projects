package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAdrenalineAt100 extends BranchTask {

    private IsUltimateReady isUltimateReady;
    private IsAdrenalineAt50 isAdrenalineAt50;

    private OpiaSlayer bot;

    public IsAdrenalineAt100(OpiaSlayer bot){
        this.bot=bot;
        isAdrenalineAt50 = new IsAdrenalineAt50(bot);
        isUltimateReady = new IsUltimateReady(bot);
    }

    @Override
    public boolean validate() {
        return Powers.getAdrenalinePercentage()>99;
    }

    @Override
    public TreeTask failureTask() {
        return isAdrenalineAt50;
    }

    @Override
    public TreeTask successTask() {
        return isUltimateReady;
    }
}