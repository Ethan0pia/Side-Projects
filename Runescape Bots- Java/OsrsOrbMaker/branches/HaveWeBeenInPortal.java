package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HaveWeBeenInPortal extends BranchTask {

    private AreWeInThePortal areWeInThePortal;
    private IsHealthLow ishealthlow;
    private OsrsOrbMaker bot;

    public HaveWeBeenInPortal(OsrsOrbMaker bot){
        this.bot=bot;
        areWeInThePortal = new AreWeInThePortal(bot);
        ishealthlow = new IsHealthLow(bot);
    }

    @Override
    public boolean validate() {
        return bot.isBeenInPortal();
    }

    @Override
    public TreeTask failureTask() {
        return areWeInThePortal;
    }

    @Override
    public TreeTask successTask() {
        return ishealthlow;
    }
}

