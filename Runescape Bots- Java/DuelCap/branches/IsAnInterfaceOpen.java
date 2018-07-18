package com.ethan0pia.bots.DuelCap.branches;

import com.ethan0pia.bots.DuelCap.OpiaDuelCap;
import com.ethan0pia.bots.DuelCap.leaves.ClickThroughDuelOptions;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsAnInterfaceOpen extends BranchTask {

    private CanOpponentBeSeen caniseeplayer;
    private ClickThroughDuelOptions clickThroughDuelOptions;

    public IsAnInterfaceOpen(OpiaDuelCap bot){
        caniseeplayer = new CanOpponentBeSeen(bot);
        clickThroughDuelOptions = new ClickThroughDuelOptions(bot);
    }

    @Override
    public boolean validate() {

        return (!Interfaces.newQuery().containers(1369,1366,1365).results().asList().isEmpty() || validateSecondInterface());
    }

    //needed due to screwy interface remaining in background after first duel.
    private boolean validateSecondInterface(){
        try {
            //will return null if not correct.
            return Interfaces.newQuery().containers(1367).textContains("Accept").results().last().isVisible();
        }catch(Exception e) {
            return false;
        }
    }


    @Override
    public TreeTask failureTask() {
        return caniseeplayer;
    }

    @Override
    public TreeTask successTask() {
        return clickThroughDuelOptions;
    }
}
