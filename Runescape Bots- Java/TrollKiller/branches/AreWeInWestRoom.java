package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.ethan0pia.bots.TrollKiller.leaves.WalkToRoom;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.rs3.local.hud.interfaces.LootInventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeInWestRoom extends BranchTask {

    private IsSpellSet isspellset;
    private WalkToRoom walkToRoom;
    private TrollKiller bot;
    private Area westCave = new Area.Circular(new Coordinate(2189,4373,0),14);

    public AreWeInWestRoom(TrollKiller bot){
        this.bot=bot;
        walkToRoom = new WalkToRoom(bot, new Coordinate(2189,4373,0));
        isspellset = new IsSpellSet(bot);
    }


    @Override
    public boolean validate() {
        if(LootInventory.isOpen()){
            LootInventory.close();
        }
        return westCave.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkToRoom;
    }

    @Override
    public TreeTask successTask() {
        return isspellset;
    }
}
