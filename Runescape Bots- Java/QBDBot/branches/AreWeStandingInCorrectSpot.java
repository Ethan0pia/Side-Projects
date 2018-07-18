package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.WalkToSpot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeStandingInCorrectSpot extends BranchTask {

    private IsQbdTargetted isqbdtargetted;
    private WalkToSpot walktospot;
    private OpiaQBD bot;

    public AreWeStandingInCorrectSpot(OpiaQBD bot){
        this.bot = bot;
        walktospot = new WalkToSpot(bot);
        isqbdtargetted = new IsQbdTargetted(bot);
    }

    @Override
    public boolean validate() {
        switch(bot.getStage()){
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walktospot;
    }

    @Override
    public TreeTask successTask() {
        return isqbdtargetted;
    }
}
