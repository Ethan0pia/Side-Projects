package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.EnterPortal;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WalkToPortal;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeByPortal extends BranchTask {

    private EnterPortal enterPortal;
    private WalkToPortal walkToPortal;
    private OsrsOrbMaker bot;
    private Area portalArea = new Area.Circular(new Coordinate(3352,3163,0),5);

    public AreWeByPortal(OsrsOrbMaker bot){
        this.bot=bot;
        enterPortal = new EnterPortal(bot);
        walkToPortal = new WalkToPortal(bot);
    }

    @Override
    public boolean validate() {
        bot.setInventoryEmptied(false);
        return portalArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkToPortal;
    }

    @Override
    public TreeTask successTask() {
        return enterPortal;
    }
}
