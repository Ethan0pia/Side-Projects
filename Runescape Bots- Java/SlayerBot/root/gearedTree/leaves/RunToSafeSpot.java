package com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class RunToSafeSpot extends LeafTask {

    private OpiaSlayer bot;
    private Coordinate jungleSafeSpot = new Coordinate(2448, 2902, 0);
    private Coordinate desertSafeSpots[]={new Coordinate(3379,3158,0),new Coordinate(3380,3159,0),new Coordinate(3380,3157,0),
            new Coordinate(3381,3156,0), new Coordinate(3381,3160,0)};

    public RunToSafeSpot(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        ViewportPath viewPath;
        if(bot.getMonster().getIndex()==104){
            int spot = Random.nextInt(0,4);
            viewPath = ViewportPath.convert(RegionPath.buildTo(desertSafeSpots[spot]));
        }else {
            viewPath = ViewportPath.convert(RegionPath.buildTo(jungleSafeSpot));
        }
        if(viewPath!=null){
            if(viewPath.step()){
                Execution.delay(2000,4000);
            }else {
                Camera.concurrentlyTurnTo(viewPath.getNext(), Random.nextDouble(0.6,0.666));
            }
        }
        bot.getUtils().stuckCheck(18);
    }
}
