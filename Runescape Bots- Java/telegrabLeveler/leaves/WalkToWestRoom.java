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
public class WalkToWestRoom extends LeafTask {

    private TelegrabLeveler bot;
    private Coordinate westRoom = new Coordinate(2189,4373,0);

    public WalkToWestRoom(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(westRoom);
            if (path != null) {
                path.step();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
