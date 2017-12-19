package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkMob;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class DoIHaveDustyKey extends BranchTask {

    private WalkMob walkmob;
    private AmIAtBankBlueDrags amiatbankbluedrags;

    public DoIHaveDustyKey(OpiaSlayer bot){
        walkmob = new WalkMob(bot);
        amiatbankbluedrags = new AmIAtBankBlueDrags(bot);
    }


    @Override
    public boolean validate() {
        //dusty key=1590
        return Inventory.contains(1590);
    }

    @Override
    public TreeTask failureTask() {
        return amiatbankbluedrags;
    }

    @Override
    public TreeTask successTask() {
        return walkmob;
    }
}
