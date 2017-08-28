package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if any item on the ground is worth over Xgp.
 */
public class ItemWorthOverX extends BranchTask {

    private GoodAssSlayerBot Bot;

    public ItemWorthOverX(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private IsInventoryFullLooting isinventoryfulllooting = new IsInventoryFullLooting(Bot);
    private IsStackable isstackable = new IsStackable(Bot);

    @Override
    public boolean validate() {

	    List<GroundItem> list = GroundItems.getLoaded().asList();
        for(GroundItem i : list) {
            if (ItemDefinition.get(i.getId()).isTradeable()|| ItemDefinition.get(i.getId()).isNoted()||ItemDefinition.get(i.getId()).stacks()) {
                int price = GrandExchange.lookup(i.getId()).getPrice();
                if(price > Bot.minVal || ItemDefinition.get(i.getId()).isNoted()||ItemDefinition.get(i.getId()).stacks()){
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public TreeTask failureTask() {
        return isstackable;
    }

    @Override
    public TreeTask successTask() {
        return isinventoryfulllooting;
    }
}
