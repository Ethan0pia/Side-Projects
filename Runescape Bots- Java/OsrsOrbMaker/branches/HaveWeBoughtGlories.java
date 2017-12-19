package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.BuySomeGlories;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.UseRoD;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class HaveWeBoughtGlories extends BranchTask {

    private UseRoD useRoD;
    private BuySomeGlories buysomeglories;
    private OsrsOrbMaker bot;

    public HaveWeBoughtGlories(OsrsOrbMaker bot){
        this.bot=bot;
        useRoD = new UseRoD(bot);
        buysomeglories = new BuySomeGlories(bot);
    }

    @Override
    public boolean validate() {
        return bot.isBoughtGlories();
    }

    @Override
    public TreeTask failureTask() {
        return buysomeglories;
    }

    @Override
    public TreeTask successTask() {
        return useRoD;
    }
}
