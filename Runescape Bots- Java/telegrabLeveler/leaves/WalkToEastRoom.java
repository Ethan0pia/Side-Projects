package com.ethan0pia.bots.telegrabLeveler.leaves;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToEastRoom extends LeafTask {

    private TelegrabLeveler bot;
    private Coordinate eastRoom = new Coordinate(2229,4384,0);

    public WalkToEastRoom(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(eastRoom);
            if (path != null) {
                path.step();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
