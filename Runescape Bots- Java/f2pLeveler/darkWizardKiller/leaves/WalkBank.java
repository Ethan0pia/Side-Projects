package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves;

import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkBank extends LeafTask {

    @Override
    public void execute() {
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(new Coordinate(2946,3370,0));
        if(path!=null){
            path.step();
        }
    }
}
