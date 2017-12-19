package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeGettingGlories extends BranchTask {

    private AreWeAtGe areweatge;
    private AreWeInClanWars areWeInClanWars;
    private OsrsOrbMaker bot;
    private Area ge = new Area.Circular(new Coordinate(3164,3485,0),10);

    public AreWeGettingGlories(OsrsOrbMaker bot){
        this.bot=bot;
        areweatge = new AreWeAtGe(bot);
        areWeInClanWars = new AreWeInClanWars(bot);
    }

    @Override
    public boolean validate() {
        bot.setBeenInPortal(false);
        if(ge.contains(bot.getPlayer()) && Bank.isOpen() && !Bank.contains("Amulet of glory(6)")){
            bot.setGetGlories(true);
        }
        return bot.isGetGlories();
    }

    @Override
    public TreeTask failureTask() {
        return areWeInClanWars;
    }

    @Override
    public TreeTask successTask() {
        return areweatge;
    }
}
