package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.OpenBank;
import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.WithdrawPreset;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsBankOpen extends BranchTask {

    private WithdrawPreset withdrawpreset = new WithdrawPreset();
    private OpenBank openbank = new OpenBank();

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return openbank;
    }

    @Override
    public TreeTask successTask() {
        return withdrawpreset;
    }
}
