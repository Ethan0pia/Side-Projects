package com.ethan0pia.bots.TanBot.leaves;

import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ConfirmXandWait extends LeafTask {

    @Override
    public void execute() {
        if(MakeXInterface.confirm(true)) {
            Execution.delayUntil(()->!MakeXInterface.isOpen(),600);
        }
    }
}
