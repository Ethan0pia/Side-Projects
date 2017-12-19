package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.OpenBank;
import com.ethan0pia.bots.WineGrabber.leaves.WithdrawPreset;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsBankOpen extends BranchTask {

    private WithdrawPreset withdrawpreset;
    private OpenBank openbank;

    public IsBankOpen(OpiaWineGrabber bot){
        openbank = new OpenBank(bot);
        withdrawpreset = new WithdrawPreset(bot);
    }

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
