package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.WalkBank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIAtBankGWD extends BranchTask {

    private IsBankOpenGWD isbankopengwd;
    private WalkBank walkbank;
    OpiaSpiritualMages bot;

    public AmIAtBankGWD(OpiaSpiritualMages bot){
        this.bot=bot;
        walkbank = new WalkBank(bot);
        isbankopengwd = new IsBankOpenGWD(bot);
    }

    @Override
    public boolean validate() {
        return bot.getUtils().amIatBank();
    }

    @Override
    public TreeTask failureTask() {
        return walkbank;
    }

    @Override
    public TreeTask successTask() {
        return isbankopengwd;
    }
}
