package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToWestRoom extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private Coordinate westRoom = new Coordinate(2189,4373,0);

    public WalkToWestRoom(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WalkToWestRoom");
            bot.setCurrentTask("Walking to the west room of the cave.");
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(westRoom);
            if (path != null) {
                if(!path.step()){
                    Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(0.5,0.85));
                }
            }else{
                BresenhamPath backup = BresenhamPath.buildTo(westRoom);
                if(backup!=null){
                    if(!backup.step()){
                        Camera.concurrentlyTurnTo(backup.getNext(), Random.nextDouble(0.5,0.85));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
