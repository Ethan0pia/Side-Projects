package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.ActivateEatingBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HaveWeActivatedEatingBot extends BranchTask {

    private ArePurpleGuysAround arepurpleguysaround;
    private ActivateEatingBot activateeatingbot;
    private OpiaQBD bot;

    public HaveWeActivatedEatingBot(OpiaQBD bot){
        this.bot = bot;
        activateeatingbot = new ActivateEatingBot(bot);
        arepurpleguysaround = new ArePurpleGuysAround(bot);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return activateeatingbot;
    }

    @Override
    public TreeTask successTask() {
        return arepurpleguysaround;
    }
}
