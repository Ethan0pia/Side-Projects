package com.ethan0pia.bots.CockroachKiller.branches;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.CockroachKiller.leaves.AttackRoach;
import com.ethan0pia.bots.CockroachKiller.leaves.OpenBank;
import com.ethan0pia.bots.CockroachKiller.leaves.WalkToCoords;
import com.ethan0pia.bots.CockroachKiller.leaves.WithdrawPreset;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsBankOpen extends BranchTask {

    private OpenBank openBank;
    private WithdrawPreset withdrawPreset;
    private Roach bot;

    public IsBankOpen(Roach bot){
        this.bot=bot;
        openBank = new OpenBank(bot);
        withdrawPreset = new WithdrawPreset(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("8");
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
