package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class DoIHaveDramenStaff extends BranchTask {

    private AmIAtLumbHouseMobWalk amiatlumbhouse;
    private AmIAtBankZygomites amiatbankzygomites;

    public DoIHaveDramenStaff(OpiaSlayer bot){
        amiatlumbhouse = new AmIAtLumbHouseMobWalk(bot);
        amiatbankzygomites = new AmIAtBankZygomites(bot);
    }


    @Override
    public boolean validate() {
        return Inventory.contains(772);
    }

    @Override
    public TreeTask failureTask() {
        return amiatbankzygomites;
    }

    @Override
    public TreeTask successTask() {
        return amiatlumbhouse;
    }
}
