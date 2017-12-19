package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class HaveWeSoldGlories extends BranchTask {

    private HaveWeBoughtGlories haveweboughtglories;
    private DoesInventoryContainNotedUnchargedGlories doesinventorycontainnotedunchargedglories;
    private OsrsOrbMaker bot;

    public HaveWeSoldGlories(OsrsOrbMaker bot){
        this.bot=bot;
        haveweboughtglories = new HaveWeBoughtGlories(bot);
        doesinventorycontainnotedunchargedglories = new DoesInventoryContainNotedUnchargedGlories(bot);
    }

    @Override
    public boolean validate() {
        return bot.isSoldGlories() && !Inventory.contains(1705);
    }

    @Override
    public TreeTask failureTask() {
        return doesinventorycontainnotedunchargedglories;
    }

    @Override
    public TreeTask successTask() {
        return haveweboughtglories;
    }
}
