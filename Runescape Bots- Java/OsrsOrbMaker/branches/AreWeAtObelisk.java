package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.CastSpellOnObelisk;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeAtObelisk extends BranchTask {

    private CastSpellOnObelisk castspellonobelisk;
    private AreWeByLadder arewebyladder;
    private OsrsOrbMaker bot;
    private Area obelisk = new Area.Circular(new Coordinate(3088,3570,0),6);

    public AreWeAtObelisk(OsrsOrbMaker bot){
        this.bot=bot;
        castspellonobelisk = new CastSpellOnObelisk(bot);
        arewebyladder = new AreWeByLadder(bot);
    }

    @Override
    public boolean validate() {
        return obelisk.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return arewebyladder;
    }

    @Override
    public TreeTask successTask() {
        return castspellonobelisk;
    }
}
