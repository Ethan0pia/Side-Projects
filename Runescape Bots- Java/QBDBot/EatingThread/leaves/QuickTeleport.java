package com.ethan0pia.bots.QBDBot.EatingThread.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class QuickTeleport extends LeafTask {

    @Override
    public void execute() {
        Environment.getLogger().debug("EatingThread:QuickTeleport");
        if(Keyboard.typeKey('\'')) {
            Execution.delay(200, 300);
        }
    }
}
