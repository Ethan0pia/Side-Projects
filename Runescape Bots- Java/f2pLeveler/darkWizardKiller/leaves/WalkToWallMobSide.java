package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves;

import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToWallMobSide extends LeafTask {

    @Override
    public void execute() {
        RegionPath path = RegionPath.buildTo(new Coordinate(2932,3355,0));
        if(path!=null){
            path.step();
        }
    }
}
