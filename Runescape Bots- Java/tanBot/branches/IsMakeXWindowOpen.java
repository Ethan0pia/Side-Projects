package com.ethan0pia.bots.TanBot.branches;

import com.ethan0pia.bots.TanBot.leaves.ConfirmXandWait;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsMakeXWindowOpen extends BranchTask {

    private ConfirmXandWait confirmxandwait = new ConfirmXandWait();
    private IsThereACrafter isthereawell = new IsThereACrafter();

    @Override
    public boolean validate() {
        return MakeXInterface.isOpen();
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
