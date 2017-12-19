package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.EnterDungeon;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.OpenTrapdoor;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsTrapdoorOpen extends BranchTask {

    private EnterDungeon enterdungeon;
    private OpenTrapdoor opentrapdoor;
    private OsrsOrbMaker bot;

    public IsTrapdoorOpen(OsrsOrbMaker bot){
        this.bot=bot;
        enterdungeon = new EnterDungeon(bot);
        opentrapdoor = new OpenTrapdoor(bot);
    }

    @Override
    public boolean validate() {
        return GameObjects.newQuery().actions("Open").names("Trapdoor").within(new Area.Circular(bot.getPlayer().getPosition(),10)).results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return opentrapdoor;
    }

    @Override
    public TreeTask successTask() {
        return enterdungeon;
    }
}
