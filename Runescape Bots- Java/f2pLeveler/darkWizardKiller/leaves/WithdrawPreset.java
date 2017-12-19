package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawPreset extends LeafTask {

    @Override
    public void execute() {
        Bank.loadPreset(1,false);
    }
}
