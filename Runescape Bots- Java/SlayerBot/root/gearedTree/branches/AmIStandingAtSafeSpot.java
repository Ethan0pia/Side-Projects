package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves.RunToSafeSpot;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AmIStandingAtSafeSpot extends BranchTask {

    private IsMobHPLow ismobhplow;
    private RunToSafeSpot runToSafeSpot;
    private OpiaSlayer bot;
    private Coordinate safeSpots[]={new Coordinate(2448, 2902, 0),new Coordinate(3379,3158,0),new Coordinate(3380,3159,0),new Coordinate(3380,3157,0),
            new Coordinate(3381,3156,0), new Coordinate(3381,3160,0)};

    public AmIStandingAtSafeSpot(OpiaSlayer bot){
        this.bot=bot;
        runToSafeSpot = new RunToSafeSpot(bot);
        ismobhplow = new IsMobHPLow(bot);
    }

    @Override
    public boolean validate() {
        for(Coordinate i:safeSpots){
            if(bot.getPlayer().distanceTo(i)<1){
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return runToSafeSpot;
    }

    @Override
    public TreeTask successTask() {
        return ismobhplow;
    }
}
