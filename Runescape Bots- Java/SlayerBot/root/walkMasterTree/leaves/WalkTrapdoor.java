package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkTrapdoor extends LeafTask {

    private OpiaSlayer bot;

    public WalkTrapdoor(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        BresenhamPath path = BresenhamPath.buildTo(new Coordinate(3094, 3471, 0));
        if (path != null) {
            if(path.step()){
                Execution.delay(2000,4000);
            }else {
                Camera.concurrentlyTurnTo(path.getNext(),Random.nextDouble(0.6,0.9));
            }
        }
        bot.getUtils().stuckCheck(46);
    }
}
