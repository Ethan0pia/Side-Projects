package com.ethan0pia.bots.CursedConversionBot.leaves;

import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ConfirmAndWait extends LeafTask {

    @Override
    public void execute() {
        if(MakeXInterface.confirm(false)){
            Execution.delay(25000,30000);
        }else if(MakeXInterface.confirm(true)){
            Execution.delay(25000,30000);
        }
    }
}
