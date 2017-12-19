package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkMaster extends LeafTask {

    private OpiaSlayer bot;
    private Area master;

    public WalkMaster(OpiaSlayer bot, Area master){
        this.bot=bot;
        this.master=master;
    }

    @Override
    public void execute() {
        if(Bank.isOpen()&& Bank.close()){
            Execution.delayUntil(()->!Bank.isOpen(),2000);
        }
        bot.getUtils().walkPath(master.getRandomCoordinate());
        bot.getUtils().stuckCheck(44);
    }
}
