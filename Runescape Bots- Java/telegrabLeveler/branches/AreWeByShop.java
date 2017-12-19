package com.ethan0pia.bots.telegrabLeveler.branches;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.telegrabLeveler.leaves.WalkToShop;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeByShop extends BranchTask {

    private IsShopOpen isshopopen;
    private WalkToShop walktoshop;
    private TelegrabLeveler bot;
    private Area shopArea = new Area.Circular(new Coordinate(2889,3523,0),4);

    public AreWeByShop(TelegrabLeveler bot){
        this.bot=bot;
        walktoshop = new WalkToShop(bot);
        isshopopen = new IsShopOpen(bot);
    }


    @Override
    public boolean validate() {
        return shopArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walktoshop;
    }

    @Override
    public TreeTask successTask() {
        return isshopopen;
    }
}
