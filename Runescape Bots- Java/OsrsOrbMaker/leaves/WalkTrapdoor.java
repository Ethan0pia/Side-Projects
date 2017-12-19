package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkTrapdoor extends LeafTask {

    private OsrsOrbMaker bot;
    private Coordinate spot = new Coordinate(3095,3469,0);

    public WalkTrapdoor(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("WalkTrapdoor");
        BresenhamPath path = BresenhamPath.buildTo(spot);
        path.step();
    }
}
