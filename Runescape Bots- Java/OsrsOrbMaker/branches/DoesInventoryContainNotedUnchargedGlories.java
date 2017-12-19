package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesInventoryContainNotedUnchargedGlories extends BranchTask {

    private IsBankOpenSellingGlories isbankopensellingglories;
    private IsBankOpenNeedSellGlories isbankopenneedsellglories;
    private OsrsOrbMaker bot;

    public DoesInventoryContainNotedUnchargedGlories(OsrsOrbMaker bot){
        this.bot=bot;
        isbankopensellingglories = new IsBankOpenSellingGlories(bot);
        isbankopenneedsellglories = new IsBankOpenNeedSellGlories(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.contains(1705);
    }

    @Override
    public TreeTask failureTask() {
        return isbankopenneedsellglories;
    }

    @Override
    public TreeTask successTask() {
        return isbankopensellingglories;
    }
}
