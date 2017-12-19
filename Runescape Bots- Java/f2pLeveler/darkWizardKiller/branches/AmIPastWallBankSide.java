package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.WalkWizards;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIPastWallBankSide extends BranchTask {

    private WalkWizards walkwizards = new WalkWizards();
    private AmIAtWallFromBank amiatwallfrombank = new AmIAtWallFromBank();

    private Area wall = new Area.Circular(new Coordinate(2932,3355,0),2);
    private Area mobSide = new Area.Rectangular(new Coordinate(2935,3358,0),new Coordinate(2897,3321,0));

    @Override
    public boolean validate() {
        return wall.contains(Players.getLocal())|| mobSide.contains(Players.getLocal());
    }

    @Override
    public TreeTask failureTask() {
        return amiatwallfrombank;
    }

    @Override
    public TreeTask successTask() {
        return walkwizards;
    }
}
