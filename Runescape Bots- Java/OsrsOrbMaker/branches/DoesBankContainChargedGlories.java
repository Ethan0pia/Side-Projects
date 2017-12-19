package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.SetNeedMoreGlories;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WithdrawEquipChargedGlory;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesBankContainChargedGlories extends BranchTask {

    private WithdrawEquipChargedGlory withdrawequipchargedglory;
    private SetNeedMoreGlories setneedmoreglories;
    private OsrsOrbMaker bot;

    public DoesBankContainChargedGlories(OsrsOrbMaker bot){
        this.bot=bot;
        withdrawequipchargedglory = new WithdrawEquipChargedGlory(bot);
        setneedmoreglories = new SetNeedMoreGlories(bot);
    }

    @Override
    public boolean validate() {
        return Bank.contains(1706) || Bank.contains(1708) || Bank.contains(1710) || Bank.contains(1712) || Bank.contains(11976) || Bank.contains(11978);
    }

    @Override
    public TreeTask failureTask() {
        return setneedmoreglories;
    }

    @Override
    public TreeTask successTask() {
        return withdrawequipchargedglory;
    }
}
