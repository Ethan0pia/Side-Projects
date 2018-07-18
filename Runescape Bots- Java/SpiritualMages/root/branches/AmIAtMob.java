package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * Checks to see if we are at the monster
 */
public class AmIAtMob extends BranchTask {

    private AreWeInCombat areWeInCombat;
    private AmIInZammyRoom amIInZammyRoom;
    private OpiaSpiritualMages bot;
    private Area mobArea = new Area.Rectangular(new Coordinate(2880,5348,0), new Coordinate(2897,5366,0));

    public AmIAtMob(OpiaSpiritualMages bot){
        this.bot=bot;
        areWeInCombat = new AreWeInCombat(bot);
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
        return areWeInCombat;
    }
}
