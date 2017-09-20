package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkMaster extends LeafTask {

    private OpiaSlayer bot;
    private Area master;
    public WalkMaster(OpiaSlayer bot, Area master){
        this.bot=bot;
        this.master=master;
    }

    @Override
    public void execute() {
        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(master.getRandomCoordinate());
        if (path != null) {
            if(!path.step(true)){
                Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(.444,.666));
            }
        }
        bot.getUtils().stuckCheck("aq");
    }
}
