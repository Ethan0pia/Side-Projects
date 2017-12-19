package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.WalkOverWall;
import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.WalkToWallMobSide;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIAtWallMobSide extends BranchTask {

    private WalkOverWall walkoverwallmobside = new WalkOverWall();
    private WalkToWallMobSide walktowallmobside = new WalkToWallMobSide();
    private Area wall = new Area.Circular(new Coordinate(2932,3355,0),2);

    @Override
    public boolean validate() {
        return wall.contains(Players.getLocal());
    }



    @Override
    public TreeTask failureTask() {
        return walktowallmobside;
    }

    @Override
    public TreeTask successTask() {
        return walkoverwallmobside;
    }
}
