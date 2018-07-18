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
public class WalkToShop extends LeafTask {

    private TelegrabLeveler bot;
    private Coordinate shopSpot = new Coordinate(2889,3523,0);

    public WalkToShop(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(shopSpot);
            if (path != null) {
                path.step();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
