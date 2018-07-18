package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldWeBank extends BranchTask {

    private IsPrayerFull isprayerfull;
    private IsQbdAround isqbdaround;
    private OpiaQBD bot;

    public ShouldWeBank(OpiaQBD bot){
        this.bot = bot;
        isqbdaround = new IsQbdAround(bot);
        isprayerfull = new IsPrayerFull(bot);
    }

    @Override
    public boolean validate() {
        return bot.isBankBool();
    }

    @Override
    public TreeTask failureTask() {
        return isqbdaround;
    }

    @Override
    public TreeTask successTask() {
        return isprayerfull;
    }
}
