package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.WalkThroughFire;
import com.runemate.game.api.hybrid.entities.Projectile;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsWallFlame extends BranchTask {

    private WalkThroughFire walkthroughfire;
    private AreWeStandingInCorrectSpot arewestandingincorrectspot;
    private OpiaQBD bot;

    public IsWallFlame(OpiaQBD bot){
        this.bot = bot;
        walkthroughfire = new WalkThroughFire(bot);
        arewestandingincorrectspot = new AreWeStandingInCorrectSpot(bot);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return arewestandingincorrectspot;
    }

    @Override
    public TreeTask successTask() {
        return walkthroughfire;
    }
}
