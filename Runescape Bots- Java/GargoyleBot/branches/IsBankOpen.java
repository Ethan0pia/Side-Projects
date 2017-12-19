package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.CloseBank;
import com.ethan0pia.bots.GargoyleBot.leaves.OpenBank;
import com.ethan0pia.bots.GargoyleBot.leaves.WithdrawPreset;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsBankOpen extends BranchTask {

    private OpenBank openBank;
    private WithdrawPreset withdrawPreset;

    private GargSlayer bot;

    public IsBankOpen(GargSlayer bot){
        this.bot=bot;
        openBank = new OpenBank(bot);
        withdrawPreset = new WithdrawPreset(bot);
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
        return withdrawPreset;
    }
}
