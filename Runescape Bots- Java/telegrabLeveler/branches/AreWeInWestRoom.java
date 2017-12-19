package com.ethan0pia.bots.telegrabLeveler.branches;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.telegrabLeveler.leaves.WalkToWestRoom;
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
    private WalkToWestRoom walktowestroom;
    private TelegrabLeveler bot;
    private Area westCave = new Area.Circular(new Coordinate(2189,4373,0),14);

    public AreWeInWestRoom(TelegrabLeveler bot){
        this.bot=bot;
        walktowestroom = new WalkToWestRoom(bot);
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
        return walktowestroom;
    }

    @Override
    public TreeTask successTask() {
        return isspellset;
    }
}
