package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.List;

/**
 * NOTES: done
 * Checks if any item on the ground is stackable.
 */
public class IsStackable extends BranchTask {

    private GoodAssSlayerBot Bot;
    private CheckGround itemList;

    public IsStackable(GoodAssSlayerBot bot, CheckGround itemList){
        Bot=bot;
        incombatatmob = new InCombatAtMob(Bot);
        this.itemList=itemList;
    }

    private IsInventoryFullStackable isinventoryfullstackable;
    private InCombatAtMob incombatatmob;

    @Override
    public boolean validate() {
        if(Bot.player!=null) {
            if (itemList.list != null) {
                for (GroundItem i : itemList.list) {
                    if (i != null && i.getDefinition()!=null && i.getId()!=15412 && i.getId()!=526) {
                        if (i.getDefinition().isNoted() || i.getDefinition().stacks()) {
                            int id;
                            if (i.getDefinition().isNoted()) {
                                id = i.getDefinition().getUnnotedId();
                            } else {
                                id = i.getId();
                            }
                            GrandExchange.Item item=null;
                            if (i.getId()!=995) {
                                item = GrandExchange.lookup(id);
                            }
                            if (ItemDefinition.get(id).isTradeable() && (item != null || i.getId()!=995)) {
                                int price = 1;//coins
                                if(Bot.stuff.containsKey(id)){
                                    price=Bot.stuff.getOrDefault(id,0);
                                }else {
                                    if (item != null) {
                                        price = item.getPrice();
                                        if (!Bot.stuff.containsKey(item.getId())) {
                                            Bot.stuff.put(id, price);
                                            if (i.getDefinition() != null && i.getDefinition().isNoted()) {
                                                Bot.stuff.put(i.getId(), price);
                                            }
                                        }
                                    }
                                }
                                if (price * i.getQuantity() > 250) {
                                    isinventoryfullstackable = new IsInventoryFullStackable(Bot, i);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return incombatatmob;
    }

    @Override
    public TreeTask successTask() {
        return isinventoryfullstackable;
    }
}
