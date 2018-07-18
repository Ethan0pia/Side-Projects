package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPlayerNull extends BranchTask {

    private EmptyLeaf emptyleaf;
    private ShouldWeBank shouldwebank;
    private OpiaQBD bot;

    public IsPlayerNull(OpiaQBD bot){
        this.bot = bot;
        shouldwebank = new ShouldWeBank(bot);
        emptyleaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        bot.setPlayer(Players.getLocal());
        return bot.getPlayer()==null;
    }

    @Override
    public TreeTask failureTask() {
        return shouldwebank;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
