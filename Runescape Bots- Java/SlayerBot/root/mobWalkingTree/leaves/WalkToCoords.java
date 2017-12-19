package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToCoords extends LeafTask {
    private OpiaSlayer bot;
    private Coordinate toWalkTo;

    public WalkToCoords(OpiaSlayer bot, Coordinate toWalkTo){
        this.bot=bot;
        this.toWalkTo=toWalkTo;
    }

    @Override
    public void execute() {
        if(Bank.isOpen()&& Bank.close()){
            Execution.delayUntil(()->!Bank.isOpen(),2000);
        }
        bot.getUtils().walkPath(toWalkTo);
        bot.getUtils().stuckCheck(48);
    }
}
