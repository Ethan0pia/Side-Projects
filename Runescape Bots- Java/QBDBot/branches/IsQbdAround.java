package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsQbdAround extends BranchTask {

    //private HaveWeActivatedEatingBot haveweactivatedeatingbot;
    private ArePurpleGuysAround arepurpleguysaround;
    private IsPhaseFour isphasefour;
    private OpiaQBD bot;

    public IsQbdAround(OpiaQBD bot){
        this.bot = bot;
        isphasefour = new IsPhaseFour(bot);
        //haveweactivatedeatingbot = new HaveWeActivatedEatingBot(bot);
        arepurpleguysaround = new ArePurpleGuysAround(bot);
    }

    @Override
    public boolean validate() {
        return !Npcs.newQuery().names("Queen Black Dragon").results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return isphasefour;
    }

    @Override
    public TreeTask successTask() {
        return arepurpleguysaround;//haveweactivatedeatingbot;
    }
}
