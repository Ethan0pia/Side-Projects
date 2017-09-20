package com.ethan0pia.bots.DuelSlave.branches;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.ethan0pia.bots.DuelSlave.leaves.ClickThroughDuelOptions;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsAnInterfaceOpen extends BranchTask {

    private CanISeePlayer caniseeplayer;
    private ClickThroughDuelOptions clickThroughDuelOptions;
    private DuelingSlave bot;

    public IsAnInterfaceOpen(DuelingSlave bot){
        this.bot=bot;
        caniseeplayer = new CanISeePlayer(bot);
        clickThroughDuelOptions = new ClickThroughDuelOptions(bot);
    }

    @Override
    public boolean validate() {

        Player player = bot.getPlayer();
        if (player != null) {

            InterfaceComponent firstScreen = Interfaces.newQuery().containers(1369).results().first();
            InterfaceComponent thirdScreen = Interfaces.newQuery().containers(1366).results().first();
            InterfaceComponent fourthScreen = Interfaces.newQuery().containers(1365).results().first();
            return (firstScreen != null || thirdScreen != null || fourthScreen!=null || check());
        }
        return false;
    }

    private boolean check(){
        InterfaceComponent secondScreen = Interfaces.newQuery().containers(1367).results().first();
        try {
            if(bot.getDuelsCompleted()==0){
                return secondScreen != null;
            }else if (secondScreen != null &&
                    Interfaces.newQuery().containers(1367).textContains("Accept").results() != null &&
                    Interfaces.newQuery().containers(1367).textContains("Accept").results().last() != null &&
                    Interfaces.newQuery().containers(1367).textContains("Accept").results().last().isVisible()) {
                return true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
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
