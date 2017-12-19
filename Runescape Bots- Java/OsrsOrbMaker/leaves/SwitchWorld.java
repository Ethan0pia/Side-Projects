package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class SwitchWorld extends LeafTask {

    private OsrsOrbMaker bot;

    public SwitchWorld(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("SwitchWorld");
        if(WorldHop.hopToRandom(true)){
            bot.setSwitchedWorlds(true);
        }
    }
}
