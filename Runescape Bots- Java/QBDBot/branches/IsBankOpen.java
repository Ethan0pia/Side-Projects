package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.OpenBank;
import com.ethan0pia.bots.QBDBot.leaves.WithdrawPreset;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsBankOpen extends BranchTask {

    private WithdrawPreset withdrawpreset;
    private OpenBank openbank;
    private OpiaQBD bot;

    public IsBankOpen(OpiaQBD bot){
        this.bot = bot;
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
