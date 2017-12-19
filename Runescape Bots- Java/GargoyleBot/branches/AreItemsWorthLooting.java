package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreItemsWorthLooting extends BranchTask {

    private IsInventoryFull isinventoryfull;
    private DoWeHaveAnItemToAlch dowehaveanitemtoalch;

    private GargSlayer bot;

    public AreItemsWorthLooting(GargSlayer bot){
        this.bot=bot;
        isinventoryfull = new IsInventoryFull(bot);
        dowehaveanitemtoalch = new DoWeHaveAnItemToAlch(bot);
    }

    @Override
    public boolean validate() {
        isinventoryfull.setItem(GroundItems.newQuery().within(new Area.Circular(bot.getPlayer().getPosition(), 8)).filter(
                i -> i != null && RegionPath.buildTo(i)!=null && bot.getPriceMap(i.getId())).results().first());
        return isinventoryfull.getItem()!=null;
    }

    @Override
    public TreeTask failureTask() {
        return dowehaveanitemtoalch;
    }

    @Override
    public TreeTask successTask() {
        return isinventoryfull;
    }
}
