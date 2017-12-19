package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Unstuck extends LeafTask {

    private Area safeSpot = new Area.Rectangular(new Coordinate(2971,3476,0),new Coordinate(2967,3479,0));
    private OpiaWineGrabber bot;

    public Unstuck(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Keyboard.typeKey(27);
            if (ChatDialog.getContinue() != null) {
                ChatDialog.getContinue().select(false);
            }
            if (!bot.getPlayer().isMoving() && !safeSpot.contains(bot.getPlayer())) {
                WebPath path = null;
                try {
                    path = Traversal.getDefaultWeb().getPathBuilder().buildTo(safeSpot.getCenter());
                } catch (Exception e) {
                    Environment.getLogger().debug("Pathing failed");
                }
                if (path != null) {
                    Execution.delayUntil(path::step, 1000);
                } else {
                    BresenhamPath backup = BresenhamPath.buildTo(safeSpot.getCenter());
                    Execution.delayUntil(backup::step, 1000);
                }
            } else {
                bot.setLeafId(0);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
