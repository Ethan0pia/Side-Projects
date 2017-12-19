package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkBank extends LeafTask {

    private OsrsOrbMaker bot;
    private Coordinate spot = new Coordinate(3369,3170,0);

    public WalkBank(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("WalkBank");
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(spot);
        if(path!=null){
            path.step();
        }
    }
}
