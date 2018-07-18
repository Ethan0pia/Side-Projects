package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

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
public class WalkToEastRoom extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private Coordinate eastRoom = new Coordinate(2229,4384,0);

    public WalkToEastRoom(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("EnterCrevice");
            bot.setCurrentTask("Walking to the eat room of the cave.");
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(eastRoom);
            if (path != null) {
                if(!path.step()){
                    Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(0.5,0.85));
                }
            }else{
                BresenhamPath backup = BresenhamPath.buildTo(eastRoom);
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
