package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.DepositInventoryWithdrawNotedUnchargedGlories;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.OpenBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsBankOpenNeedSellGlories extends BranchTask {

    private DepositInventoryWithdrawNotedUnchargedGlories depositinventorywithdrawnotedunchargedglories;
    private OpenBank openbank;
    private OsrsOrbMaker bot;

    public IsBankOpenNeedSellGlories(OsrsOrbMaker bot){
        this.bot=bot;
        depositinventorywithdrawnotedunchargedglories = new DepositInventoryWithdrawNotedUnchargedGlories(bot);
        openbank = new OpenBank(bot);
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
        return depositinventorywithdrawnotedunchargedglories;
    }
}
