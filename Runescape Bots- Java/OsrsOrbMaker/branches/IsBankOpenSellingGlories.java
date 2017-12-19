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
public class IsBankOpenSellingGlories extends BranchTask {

    private CloseBank closebank;
    private IsSellWindowOpen issellwindowopen;
    private OsrsOrbMaker bot;

    public IsBankOpenSellingGlories(OsrsOrbMaker bot){
        this.bot=bot;
        closebank = new CloseBank(bot);
        issellwindowopen = new IsSellWindowOpen(bot);
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return issellwindowopen;
    }

    @Override
    public TreeTask successTask() {
        return closebank;
    }
}
