package com.ethan0pia.bots.QBDBot.EatingThread.branches;

import com.ethan0pia.bots.QBDBot.EatingThread.leaves.ActivatePrayers;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class AreQuickPrayersOn extends BranchTask {

    private AreStatPotionsActive arestatepotionsactive = new AreStatPotionsActive();
    private ActivatePrayers activateprayers = new ActivatePrayers();

    @Override
    public boolean validate() {
        Environment.getLogger().debug("EatingThread:AreQuickPrayersOn");
        return Powers.Prayer.isQuickPraying();
    }

    @Override
    public TreeTask failureTask() {
        return activateprayers;
    }

    @Override
    public TreeTask successTask() {
        return arestatepotionsactive;
    }
}
