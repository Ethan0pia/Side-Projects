package com.ethan0pia.bots.WineGrabberUltra.shiftChange.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.shiftChange.leaves.OpenBankEdgeville;
import com.ethan0pia.bots.WineGrabberUltra.shiftChange.leaves.WithdrawEverythingBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsBankOpen extends BranchTask {

    private WithdrawEverythingBank withdrawEverythingBank;
    private OpenBankEdgeville openBank;
    private OpiaWineGrabberUltra bot;

    public IsBankOpen(OpiaWineGrabberUltra bot){
        this.bot=bot;
        withdrawEverythingBank = new WithdrawEverythingBank(bot);
        openBank = new OpenBankEdgeville(bot);
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return openBank;
    }

    @Override
    public TreeTask successTask() {
        return withdrawEverythingBank;
    }
}
