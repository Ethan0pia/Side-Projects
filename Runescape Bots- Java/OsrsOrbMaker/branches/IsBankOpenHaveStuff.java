package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.CloseBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsBankOpenHaveStuff extends BranchTask {

    private CloseBank closebankhavestuff;
    private HaveWeBeenInPortal haveWeBeenInPortal;
    private OsrsOrbMaker bot;

    public IsBankOpenHaveStuff(OsrsOrbMaker bot){
        this.bot=bot;
        closebankhavestuff = new CloseBank(bot);
        haveWeBeenInPortal = new HaveWeBeenInPortal(bot);
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return haveWeBeenInPortal;
    }

    @Override
    public TreeTask successTask() {
        return closebankhavestuff;
    }
}
