package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WithdrawMonkfish;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WithdrawOrbs;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesInventoryContainFood extends BranchTask {

    private WithdrawOrbs withdrawOrbs;
    private WithdrawMonkfish withdrawmonkfish;
    private OsrsOrbMaker bot;

    public DoesInventoryContainFood(OsrsOrbMaker bot){
        this.bot=bot;
        withdrawOrbs = new WithdrawOrbs(bot);
        withdrawmonkfish = new WithdrawMonkfish(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.contains(329);
    }

    @Override
    public TreeTask failureTask() {
        return withdrawmonkfish;
    }

    @Override
    public TreeTask successTask() {
        return withdrawOrbs;
    }
}
