package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPrayerFull extends BranchTask {

    private AreWeAtBank areweatbank;
    private AreWeByAltar arewebyaltar;
    private OpiaQBD bot;

    public IsPrayerFull(OpiaQBD bot){
        this.bot = bot;
        arewebyaltar = new AreWeByAltar(bot);
        areweatbank = new AreWeAtBank(bot);
    }

    @Override
    public boolean validate() {
        return Powers.Prayer.getMaximumPoints() == Powers.Prayer.getPoints();
    }

    @Override
    public TreeTask failureTask() {
        return arewebyaltar;
    }

    @Override
    public TreeTask successTask() {
        return areweatbank;
    }
}
