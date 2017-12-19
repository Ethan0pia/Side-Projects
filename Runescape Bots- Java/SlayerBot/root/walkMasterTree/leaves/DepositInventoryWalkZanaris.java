package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class DepositInventoryWalkZanaris extends LeafTask {

    private OpiaSlayer bot;
    public DepositInventoryWalkZanaris(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Bank.depositInventory();
        bot.getUtils().stuckCheck(37);
    }
}
