package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.EmptyLeaf;
import com.ethan0pia.bots.QBDBot.leaves.TargetQBD;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsQbdTargetted extends BranchTask {

    private EmptyLeaf emptyleaf;
    private TargetQBD targetqbd;
    private OpiaQBD bot;

    public IsQbdTargetted(OpiaQBD bot){
        this.bot = bot;
        targetqbd = new TargetQBD(bot);
        emptyleaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        return bot.getPlayer().getTarget()!=null && bot.getPlayer().getTarget().getName().equals("Queen Black Dragon");
    }

    @Override
    public TreeTask failureTask() {
        return targetqbd;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
