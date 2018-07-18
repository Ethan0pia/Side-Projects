package com.ethan0pia.bots.WineGrabberUltra.grabber.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.OpenBank;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.WithdrawPreset;
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

    public IsBankOpen(OpiaWineGrabberUltra bot){
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
