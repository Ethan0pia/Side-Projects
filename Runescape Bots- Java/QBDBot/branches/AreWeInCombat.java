package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.QuickTeleport;
import com.ethan0pia.bots.QBDBot.leaves.WalkAltar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeInCombat extends BranchTask {

    private QuickTeleport quickteleport;
    private WalkAltar walkaltar;
    private OpiaQBD bot;

    public AreWeInCombat(OpiaQBD bot){
        this.bot = bot;
        walkaltar = new WalkAltar(bot);
        quickteleport = new QuickTeleport(bot);
    }

    @Override
    public boolean validate() {
        return bot.getPlayer().getHealthGauge()!=null;
    }

    @Override
    public TreeTask failureTask() {
        return walkaltar;
    }

    @Override
    public TreeTask successTask() {
        return quickteleport;
    }
}
