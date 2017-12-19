package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * Checks to see if we are at the monster
 */
public class AmIAtMob extends BranchTask {

    private AreItemsWorthLooting areItemsOnGroundWorthX;
    private AmIInZammyRoom amIInZammyRoom;
    private OpiaSpiritualMages bot;
    private Area mobArea = new Area.Circular(new Coordinate(2887,5358,0), 12);

    public AmIAtMob(OpiaSpiritualMages bot){
        this.bot=bot;
        areItemsOnGroundWorthX = new AreItemsWorthLooting(bot);
        amIInZammyRoom = new AmIInZammyRoom(bot);
    }

    @Override
    public boolean validate() {
        return mobArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amIInZammyRoom;
    }

    @Override
    public TreeTask successTask() {
        return areItemsOnGroundWorthX;
    }
}
