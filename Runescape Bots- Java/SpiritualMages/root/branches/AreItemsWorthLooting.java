package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.ethan0pia.bots.SpiritualMages.root.leaves.AlchItem;
import com.ethan0pia.bots.SpiritualMages.root.leaves.AttackMob;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.ArrayList;
import java.util.List;

/**
 * NOTES: done
 * Are items on the ground worth X or are noteable/stackable
 */
public class AreItemsWorthLooting extends BranchTask {

    private DoWeHaveRoomForLoot doWeHaveRoomForLoot;
    private AttackMob attackMob;
    private AlchItem alchItem;
    private OpiaSpiritualMages bot;
    private boolean emergencyAlching = false;
    private Area mobArea = new Area.Rectangular(new Coordinate(2880,5348,0), new Coordinate(2897,5366,0));


    public AreItemsWorthLooting(OpiaSpiritualMages bot){
        this.bot=bot;
        attackMob = new AttackMob(bot);
        doWeHaveRoomForLoot = new DoWeHaveRoomForLoot(bot);
        alchItem = new AlchItem(bot);
    }

    @Override
    public boolean validate() {
        if(bot.isGroundItemsEmpty()) {
            bot.setGroundItems(GroundItems.newQuery().within(mobArea).filter(i -> bot.priceMapContains(i.getId())).results().asList());
        }

        if (!bot.isGroundItemsEmpty()) {
            doWeHaveRoomForLoot.setItem(bot.getGroundItem());
            return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        if(bot.alchItemLength()>3 || (bot.alchItemLength()!=0 && emergencyAlching)){
            emergencyAlching = true;
            return alchItem;
        }
        emergencyAlching = false;
        return attackMob;
    }

    @Override
    public TreeTask successTask() {
        return doWeHaveRoomForLoot;
    }
}
