package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.CloseBank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Is the bank open?
 */
public class IsBankOpenGeared extends BranchTask {

    private CloseBank closebank;
    private AreWeInGWD walkmob;
    private OpiaSpiritualMages bot;

    public IsBankOpenGeared(OpiaSpiritualMages bot){
        this.bot=bot;
        closebank = new CloseBank(bot);
        walkmob = new AreWeInGWD(bot);
    }

    @Override
    public boolean validate() {
        //mobs that need to access bank to walk to mob.
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walkmob;
    }

    @Override
    public TreeTask successTask() {
        return closebank;
    }
}
