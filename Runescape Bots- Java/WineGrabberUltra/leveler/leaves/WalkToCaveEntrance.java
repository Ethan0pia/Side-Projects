package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
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
public class WalkToCaveEntrance extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private Coordinate caveEntrance = new Coordinate(2878,3572,0);

    public WalkToCaveEntrance(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WalkToCaveEntrance");
            bot.setCurrentTask("Walking to the cave entrance.");
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(caveEntrance);
            if (path != null) {
                if(!path.step()){
                    Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(0.5,0.85));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
