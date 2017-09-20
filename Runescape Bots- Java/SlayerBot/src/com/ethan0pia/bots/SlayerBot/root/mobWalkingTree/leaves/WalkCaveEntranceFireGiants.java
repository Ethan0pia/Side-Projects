package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WalkCaveEntranceFireGiants extends LeafTask {

    private OpiaSlayer bot;

    public WalkCaveEntranceFireGiants(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(new Coordinate(2745,3152,0));
        if(path!=null){
            if(!path.step(true)){
                Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(.444,.666));
            }
        }
        bot.getUtils().stuckCheck("ae");
    }
}