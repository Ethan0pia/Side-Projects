package com.ethan0pia.bots.TrollKiller.leaves;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToRoom extends LeafTask {

    private TrollKiller bot;
    private Coordinate spot;

    public WalkToRoom(TrollKiller bot, Coordinate spot){
        this.bot=bot;
        this.spot = spot;
    }


    @Override
    public void execute() {
        try {
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(spot);
            if (path != null) {
                path.step();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
