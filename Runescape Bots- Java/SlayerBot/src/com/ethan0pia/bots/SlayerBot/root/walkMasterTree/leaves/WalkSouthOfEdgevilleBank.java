package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkSouthOfEdgevilleBank extends LeafTask {

    private OpiaSlayer bot;
    public WalkSouthOfEdgevilleBank(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(new Coordinate(3092,3485,0));
        if (path != null) {
            if(!path.step(true)){
                Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(.444,.666));
            }
        }
        bot.getUtils().stuckCheck("ar");
    }
}
