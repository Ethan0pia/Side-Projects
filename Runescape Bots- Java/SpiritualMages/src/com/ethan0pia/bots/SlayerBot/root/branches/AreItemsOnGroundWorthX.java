package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Are items on the ground worth X or are noteable/stackable
 */
public class AreItemsOnGroundWorthX extends BranchTask {

    private IsInventoryFullOrContainsStackableAlready isinventoryfullorcontainsstackablealready;
    private AmIInCombat amIInCombat;
    private OpiaSpiritualMages bot;
    private GroundItem oldItem;
    private int loops;


    public AreItemsOnGroundWorthX(OpiaSpiritualMages bot){
        this.bot=bot;
        amIInCombat = new AmIInCombat(bot);
        isinventoryfullorcontainsstackablealready = new IsInventoryFullOrContainsStackableAlready(bot);
        loops=0;
    }

    @Override
    public boolean validate() {
        GroundItem item = GroundItems.newQuery().within(new Area.Circular(bot.getPlayer().getPosition(), 10)).filter(
                i -> i != null && (((i.getDefinition().isTradeable() && checkPriceUnnoted(i) > 2500) || (i.getDefinition().isTradeable() && i.getDefinition().stacks() && checkPriceUnnoted(i) > 300)
                        || (i.getDefinition().isNoted() && checkPriceNoted(i) > 300) || (i.getId() == 995 && i.getQuantity() > 1500)))).results().first();

        if (item != null) {
            if (oldItem == null) {
                oldItem = item;
                loops = 0;
            }
            if (oldItem.equals(item) || oldItem == item) {
                loops++;
                if (loops == 8) {
                    Environment.getLogger().debug("Failed to pick up item.");
                }
                if (loops > 8) {
                    return false;
                } else {
                    isinventoryfullorcontainsstackablealready.setItem(item);
                    return true;
                }
            } else {
                loops = 0;
                isinventoryfullorcontainsstackablealready.setItem(item);
                oldItem = null;
                return true;
            }
        }
        return false;
    }

    //helper for checkGround
    private int checkPriceUnnoted(GroundItem item){
        int price = 0;
        if(item!=null) {
            int itemId = item.getId();
            if (bot.getPriceMap(itemId)) {
                return bot.getValue(itemId);
            } else {
                GrandExchange.Item geItem = GrandExchange.lookup(itemId);
                if (geItem != null) {
                    price = geItem.getPrice();
                    bot.put(itemId, price);
                    int notedId = item.getDefinition().getNotedId();
                    bot.put(notedId, price);
                }
            }
        }
        return price;
    }

    //helper for checkGround
    private int checkPriceNoted(GroundItem item){
        int price = 0;
        if(item!=null) {
            int itemId = item.getId();
            if (bot.getPriceMap(itemId)) {
                return bot.getValue(itemId);
            } else {
                if (item.getDefinition() != null) {
                    ItemDefinition definition = item.getDefinition();
                    if (definition != null) {
                        int id = definition.getUnnotedId();
                        GrandExchange.Item geItem = GrandExchange.lookup(id);
                        if (geItem != null) {
                            price = geItem.getPrice();
                            bot.put(itemId, price);
                            bot.put(id, price);
                        }
                    }
                }
            }
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
