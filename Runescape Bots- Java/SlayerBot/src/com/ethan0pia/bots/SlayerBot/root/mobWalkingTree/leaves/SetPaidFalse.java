package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class SetPaidFalse extends LeafTask {

    private OpiaSlayer bot;

    public SetPaidFalse(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        bot.setPaid(false);

        bot.getUtils().stuckCheck("ac");
    }
}
