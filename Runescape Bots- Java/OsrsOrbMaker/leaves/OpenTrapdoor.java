package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class OpenTrapdoor extends LeafTask {

    private OsrsOrbMaker bot;

    public OpenTrapdoor(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("OpenTrapdoor");
        GameObject trapdoor = GameObjects.newQuery().names("Trapdoor").within(new Area.Circular(bot.getPlayer().getPosition(),10)).actions("Open").results().nearest();
        if(trapdoor!=null){
            if(trapdoor.interact("Open")){
                Execution.delayUntil(()->bot.getPlayer().getAnimationId()==-1,1200);
            }
        }
    }
}
