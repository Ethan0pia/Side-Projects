package com.ethan0pia.bots.QBDBot.EatingThread.branches;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class IsPrayerGood extends BranchTask {

    private AreQuickPrayersOn arequickprayerson = new AreQuickPrayersOn();
    private DoWeHavePrayerPotion dowehaveprayerpotion = new DoWeHavePrayerPotion();

    @Override
    public boolean validate() {
        Environment.getLogger().debug("EatingThread:IsPrayerGood");
        return Powers.Prayer.getPoints()>100;
    }

    @Override
    public TreeTask failureTask() {
        return dowehaveprayerpotion;
    }

    @Override
    public TreeTask successTask() {
        return arequickprayerson;
    }
}
