package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Sets bank boolean to true to signify banked this run.
 */
public class SetBankBooleanTrue extends LeafTask {

    private OpiaSlayer bot;
    public SetBankBooleanTrue(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        bot.setInventoryEmptied(true);
        bot.setBankBool(true);

        bot.getUtils().stuckCheck("g");
    }
}
