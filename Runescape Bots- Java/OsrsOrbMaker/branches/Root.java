package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Root extends BranchTask {

    private AreWeInLumbridge areWeInLumbridge;
    private EmptyLeaf emptyLeaf;
    private OsrsOrbMaker bot;

    public Root(OsrsOrbMaker bot){
        this.bot=bot;
        areWeInLumbridge = new AreWeInLumbridge(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        bot.setPlayer(Players.getLocal());
        return bot.getPlayer()!=null;
    }

    @Override
    public TreeTask failureTask() {
        return emptyLeaf;
    }

    @Override
    public TreeTask successTask() {
        return areWeInLumbridge;
    }
}
