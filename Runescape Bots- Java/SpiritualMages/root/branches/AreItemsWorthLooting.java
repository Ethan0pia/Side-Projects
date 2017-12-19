package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.AttackMob;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Are items on the ground worth X or are noteable/stackable
 */
public class AreItemsWorthLooting extends BranchTask {

    private DoWeHaveRoomForLoot isinventoryfullorcontainsstackablealready;
    private AttackMob amIInCombat;
    private OpiaSpiritualMages bot;
    private Area mobArea = new Area.Circular(new Coordinate(2887,5358,0), 15);


    public AreItemsWorthLooting(OpiaSpiritualMages bot){
        this.bot=bot;
        amIInCombat = new AttackMob(bot);
        isinventoryfullorcontainsstackablealready = new DoWeHaveRoomForLoot(bot);
    }

    @Override
    public boolean validate() {
        GroundItem item = GroundItems.newQuery().within(mobArea).filter(
                i -> (priceCheck(i)>3000 || priceCheck(i)<=1) && ((i.getId() == 995 && i.getQuantity() > 2000) || i.getId()!=995)).results().first();

        if (item != null) {
            isinventoryfullorcontainsstackablealready.setItem(item);
            return true;
        }
        return false;
    }

    private int priceCheck(GroundItem item){
        int price = 0;
        if(item!=null && item.getDefinition()!=null) {
            ItemDefinition baseDefinition = item.getDefinition();
            int id = item.getId();
            if (!bot.priceMapContains(id)) {
                if (baseDefinition.isNoted()) {
                    int itemID = baseDefinition.getUnnotedId();
                    ItemDefinition definition = ItemDefinition.get(itemID);
                    if (definition != null && definition.isTradeable()) {
                        GrandExchange.Item geItem = GrandExchange.lookup(itemID);
                        if (geItem != null) {
                            price = geItem.getPrice();
                            bot.put(id, price);
                            bot.put(itemID, price);
                        }
                    } else {
                        bot.put(id, 0);
                        bot.put(itemID, 0);
                    }
                } else{
                    if(baseDefinition.isTradeable()){
                        GrandExchange.Item geItem = GrandExchange.lookup(id);
                        if (geItem != null) {
                            price = geItem.getPrice();
                            bot.put(id, price);
                        }
                    }else{
                        bot.put(id, 0);
                    }
                }
            }else{
                price=bot.getPrice(item.getId());
            }
            return price*item.getQuantity();
        }

        return price;
    }

    @Override
    public TreeTask failureTask() {
        return amIInCombat;
    }

    @Override
    public TreeTask successTask() {
        return isinventoryfullorcontainsstackablealready;
    }
}
