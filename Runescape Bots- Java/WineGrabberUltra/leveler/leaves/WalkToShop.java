package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionWindow;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WalkToShop extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private Coordinate shopSpot = new Coordinate(2889,3523,0);

    public WalkToShop(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WalkToShop");
            bot.setCurrentTask("Walking to the shop.");
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(shopSpot);
            if (path != null) {
                if(!path.step()){
                    Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(0.5,0.85));
                }
            } else {
                BresenhamPath backup = BresenhamPath.buildTo(shopSpot);
                if (backup != null) {
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
