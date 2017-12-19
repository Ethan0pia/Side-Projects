package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.SetPortalBool;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeInThePortal extends BranchTask {

    private SetPortalBool setPortalBool;
    private AreWeByPortal areWeByPortal;
    private OsrsOrbMaker bot;
    private Area portal = new Area.Circular(new Coordinate(3326,4754,0),20);

    public AreWeInThePortal(OsrsOrbMaker bot){
        this.bot=bot;
        setPortalBool = new SetPortalBool(bot);
        areWeByPortal = new AreWeByPortal(bot);
    }

    @Override
    public boolean validate() {
        return portal.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return areWeByPortal;
    }

    @Override
    public TreeTask successTask() {
        return setPortalBool;
    }
}
