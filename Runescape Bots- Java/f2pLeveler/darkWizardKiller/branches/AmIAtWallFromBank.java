package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.WalkOverWall;
import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.WalkToWallBankSide;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIAtWallFromBank extends BranchTask {

    private WalkOverWall walkoverwall = new WalkOverWall();
    private WalkToWallBankSide walktowallbankside = new WalkToWallBankSide();
    private Area wall = new Area.Circular(new Coordinate(2938,3355,0),2);

    @Override
    public boolean validate() {
        return wall.contains(Players.getLocal());
    }

    @Override
    public TreeTask failureTask() {
        return walktowallbankside;
    }

    @Override
    public TreeTask successTask() {
        return walkoverwall;
    }
}
