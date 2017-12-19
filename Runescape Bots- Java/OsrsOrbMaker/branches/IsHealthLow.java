package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsHealthLow extends BranchTask {

    private DoWeHaveFood dowehavefood;
    private AreWeAtObelisk areweatobelisk;
    private OsrsOrbMaker bot;
    private Area portal = new Area.Circular(new Coordinate(3326,4754,0),20);
    private Area clanWars = new Area.Circular(new Coordinate(3369,3170,0),30);

    public IsHealthLow(OsrsOrbMaker bot){
        this.bot=bot;
        dowehavefood = new DoWeHaveFood(bot);
        areweatobelisk = new AreWeAtObelisk(bot);
    }

    @Override
    public boolean validate() {
        return Health.getCurrent()<12 && !portal.contains(bot.getPlayer()) && !clanWars.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return areweatobelisk;
    }

    @Override
    public TreeTask successTask() {
        return dowehavefood;
    }
}
