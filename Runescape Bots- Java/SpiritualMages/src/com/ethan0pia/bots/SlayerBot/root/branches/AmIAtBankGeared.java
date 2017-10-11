package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Are we at the bank and geared?
 */
public class AmIAtBankGeared extends BranchTask {

    private IsBankOpenGeared isbankopengeared;
    private AmIAtMob amiatmob;
    private OpiaSpiritualMages bot;

    public AmIAtBankGeared(OpiaSpiritualMages bot){
        this.bot=bot;
        isbankopengeared = new IsBankOpenGeared(bot);
        amiatmob = new AmIAtMob(bot);
    }


    @Override
    public boolean validate() {
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
