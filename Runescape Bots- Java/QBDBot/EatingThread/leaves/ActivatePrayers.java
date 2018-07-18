package com.ethan0pia.bots.QBDBot.EatingThread.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class ActivatePrayers extends LeafTask {

    @Override
    public void execute() {
        Environment.getLogger().debug("EatingThread:ActivatePrayers");
        Powers.Prayer.toggleQuickPrayers();
        Execution.delay(200,400);
    }
}
