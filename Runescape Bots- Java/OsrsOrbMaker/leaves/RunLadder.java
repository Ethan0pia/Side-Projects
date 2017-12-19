package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class RunLadder extends LeafTask {

    private OsrsOrbMaker bot;
    private Coordinate spot = new Coordinate(3088, 9970,0);
    private Area gate = new Area.Circular(new Coordinate(3109,9941,0),6);

    public RunLadder(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("RunLadder");
        if(!bot.isPassedGate() && gate.contains(bot.getPlayer())){
            GameObject gateToOpen = GameObjects.newQuery().names("Gate").actions("Open").within(new Area.Circular(bot.getPlayer().getPosition(),5)).results().nearest();
            if(gateToOpen!=null){
                gateToOpen.interact("Open");
            }else{
                bot.setPassedGate(true);
            }
        }else if(bot.isPassedGate()) {
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(spot);
            if (path != null) {
                Execution.delayUntil(path::step, 2000);
            }
        }else{
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(gate.getCenter());
            if (path != null) {
                Execution.delayUntil(path::step, 2000);
            }
        }
    }
}
