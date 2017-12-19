package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves;

import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToWallBankSide extends LeafTask {

    @Override
    public void execute() {
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(new Coordinate(2938,3355,0));
        if(path!=null){
            path.step();
        }
    }
}
