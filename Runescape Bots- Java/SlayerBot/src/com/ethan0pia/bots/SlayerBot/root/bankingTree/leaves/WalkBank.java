package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class WalkBank extends LeafTask {
    private OpiaSlayer bot;
    private Area camelotBank = new Area.Rectangular(new Coordinate(2721,3493, 0),new Coordinate(2729,3490,0));

    public WalkBank(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Player player = bot.getPlayer();
        if (player != null) {
            if(Camera.getPitch()<0.4){
                Camera.concurrentlyTurnTo(Random.nextDouble(0.55,0.66));
            }
            if (!camelotBank.contains(player)) {
                final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(camelotBank.getRandomCoordinate());
                if (path != null) {
                    if(!path.step(true)){
                        Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(.444,.666));
                    }
                }
            }
        }
        bot.getUtils().stuckCheck("h");
    }
}
