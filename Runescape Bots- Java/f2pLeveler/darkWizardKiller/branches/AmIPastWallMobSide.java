package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.WalkBank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIPastWallMobSide extends BranchTask {

    private WalkBank walkbank = new WalkBank();
    private AmIAtWallMobSide amiatwall = new AmIAtWallMobSide();
    private Area wall = new Area.Circular(new Coordinate(2938,3355,0),2);
    private Area falador = new Area.Rectangular(new Coordinate(2937,3380,0), new Coordinate(2966,3340,0));

    @Override
    public boolean validate() {
        return wall.contains(Players.getLocal())||falador.contains(Players.getLocal());
    }




    @Override
    public TreeTask failureTask() {
        return amiatwall;
    }

    @Override
    public TreeTask successTask() {
        return walkbank;
    }
}
