package com.ethan0pia.bots.wellBot.branches;

import com.ethan0pia.bots.wellBot.leaves.ConfirmXandWait;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.List;


/**
 * NOTES:
 * 
 */
public class IsMakeXWindowOpen extends BranchTask {

    private ConfirmXandWait confirmxandwait = new ConfirmXandWait();
    private IsThereAWell isthereawell = new IsThereAWell();
    private InterfaceComponent takeWindow = Interfaces.newQuery().containers(96272386).results().first();

    @Override
    public boolean validate() {
       if(takeWindow==null){
           takeWindow = Interfaces.newQuery().containers(96272386).results().first();
       }
        return takeWindow!=null && takeWindow.isValid() && takeWindow.isVisible();
    }

    @Override
    public TreeTask failureTask() {
        return isthereawell;
    }

    @Override
    public TreeTask successTask() {
        return confirmxandwait;
    }
}
