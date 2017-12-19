package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkGargoyles extends LeafTask {

    private GargSlayer bot;
    private Area gargs = new Area.Circular(new Coordinate(3441,3563,2),3);

    public WalkGargoyles(GargSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(gargs.getRandomCoordinate());
        if(path!=null){
            path.step();
        }
    }
}
