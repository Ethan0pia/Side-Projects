package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkSouthOfEdgevilleBank extends LeafTask {

    private OpiaSlayer bot;

    public WalkSouthOfEdgevilleBank(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(Bank.isOpen()&& Bank.close()){
            Execution.delayUntil(()->!Bank.isOpen(),2000);
        }
        bot.getUtils().walkPath(new Coordinate(3092,3485,0));
        bot.getUtils().stuckCheck(45);
    }
}
