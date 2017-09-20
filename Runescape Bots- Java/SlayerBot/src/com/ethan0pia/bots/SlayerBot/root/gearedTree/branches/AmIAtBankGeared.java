package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Are we at the bank and geared?
 */
public class AmIAtBankGeared extends BranchTask {

    private IsBankOpenGeared isbankopengeared;
    private AmIAtMob amiatmob;
    private OpiaSlayer bot;

    public AmIAtBankGeared(OpiaSlayer bot){
        this.bot=bot;
        isbankopengeared = new IsBankOpenGeared(bot);
        amiatmob = new AmIAtMob(bot);
    }


    @Override
    public boolean validate() {
        bot.setInventoryEmptied(false);
        return bot.getUtils().amIatBank();
    }

    @Override
    public TreeTask failureTask() {
        return amiatmob;
    }

    @Override
    public TreeTask successTask() {
        return isbankopengeared;
    }
}
