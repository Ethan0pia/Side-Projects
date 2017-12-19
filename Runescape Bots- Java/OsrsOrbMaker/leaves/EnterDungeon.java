package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EnterDungeon extends LeafTask {

    private OsrsOrbMaker bot;
    private Area dungeon = new Area.Circular(new Coordinate(3104,9911,0),150);

    public EnterDungeon(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("EnterDungeon");
        GameObject trapdoor = GameObjects.newQuery().names("Trapdoor").within(new Area.Circular(bot.getPlayer().getPosition(),10)).actions("Climb-down").results().nearest();
        if(trapdoor!=null){
            if(trapdoor.interact("Climb-down")){
                bot.setPassedGate(false);
                Execution.delayUntil(()->dungeon.contains(bot.getPlayer()),1500);
            }
        }
    }
}
