package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.ClickGe;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.SellGlories;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsSellWindowOpen extends BranchTask {

    private SellGlories sellglories;
    private ClickGe clickge;
    private OsrsOrbMaker bot;

    public IsSellWindowOpen(OsrsOrbMaker bot){
        this.bot=bot;
        clickge = new ClickGe(bot);
        sellglories = new SellGlories(bot);
    }

    @Override
    public boolean validate() {
        return GrandExchange.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return clickge;
    }

    @Override
    public TreeTask successTask() {
        return sellglories;
    }
}
