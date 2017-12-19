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
public class ClimbLadder extends LeafTask {

    private OsrsOrbMaker bot;
    private Area obeliskArea = new Area.Circular(new Coordinate(3088,3570,0),6);

    public ClimbLadder(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("ClimbLadder");
        GameObject ladder = GameObjects.newQuery().names("Ladder").results().nearest();
        if(ladder!=null){
            if(ladder.interact("Climb-up")){
                Execution.delayUntil(()->obeliskArea.contains(bot.getPlayer()),3000);
            }
        }
    }
}
