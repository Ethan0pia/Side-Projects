package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeInClanWars extends BranchTask {

    private AreWeAtClanWarsBank areWeAtClanWarsBank;
    private DoWeHaveRoDNeedTele doWeHaveRoDNeedTele;
    private OsrsOrbMaker bot;
    private Area clanWars = new Area.Circular(new Coordinate(3369,3170,0),30);

    public AreWeInClanWars(OsrsOrbMaker bot){
        this.bot=bot;
        areWeAtClanWarsBank = new AreWeAtClanWarsBank(bot);
        doWeHaveRoDNeedTele = new DoWeHaveRoDNeedTele(bot);
    }

    @Override
    public boolean validate() {
        return clanWars.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return doWeHaveRoDNeedTele;
    }

    @Override
    public TreeTask successTask() {
        return areWeAtClanWarsBank;
    }
}
