package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.ActivatePillar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class CanPillarsActivate extends BranchTask {

    private ActivatePillar activatepillar;
    private IsWallFlame iswallflame;
    private OpiaQBD bot;

    public CanPillarsActivate(OpiaQBD bot){
        this.bot = bot;
        iswallflame = new IsWallFlame(bot);
        activatepillar = new ActivatePillar(bot);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return iswallflame;
    }

    @Override
    public TreeTask successTask() {
        return activatepillar;
    }
}
