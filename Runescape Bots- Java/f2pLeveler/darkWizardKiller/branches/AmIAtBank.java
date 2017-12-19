package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIAtBank extends BranchTask {

    private IsBankOpen isbankopen = new IsBankOpen();
    private AmIPastWallMobSide amipastwallmobside = new AmIPastWallMobSide();
    private Area bank = new Area.Circular(new Coordinate(2946,3370,0),8);

    @Override
    public boolean validate() {
        return bank.contains(Players.getLocal());
    }

    @Override
    public TreeTask failureTask() {
        return amipastwallmobside;
    }

    @Override
    public TreeTask successTask() {
        return isbankopen;
    }
}
