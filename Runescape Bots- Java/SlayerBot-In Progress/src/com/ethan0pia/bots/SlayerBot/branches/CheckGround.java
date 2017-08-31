package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * NOTES: done
 * Are there items on the ground?
 */
public class CheckGround extends BranchTask {

    private GoodAssSlayerBot Bot;

    public CheckGround(GoodAssSlayerBot bot){
        Bot=bot;
        itemworthoverx = new ItemWorthOverX(bot, this);
        incombatatmob = new InCombatAtMob(bot);
    }

    private ItemWorthOverX itemworthoverx;
    private InCombatAtMob incombatatmob;
    public List<GroundItem> list;

    @Override
    public boolean validate() {
        if(Bot.player!=null) {
            list = GroundItems.newQuery().within(new Area.Circular(Bot.player.getPosition(), 12)).reachable().results().asList();
            return list!=null;
        }

       return false;
    }

    @Override
    public TreeTask failureTask() {
        return incombatatmob;
    }

    @Override
    public TreeTask successTask() {
        return itemworthoverx;
    }
}
