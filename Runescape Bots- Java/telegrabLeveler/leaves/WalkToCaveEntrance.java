package com.ethan0pia.bots.TelegrabLeveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToCaveEntrance extends LeafTask {

    private TelegrabLeveler bot;
    private Coordinate caveEntrance = new Coordinate(2878,3572,0);

    public WalkToCaveEntrance(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(caveEntrance);
            if (path != null) {
                path.step();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
