package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.EnterLootRoom;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeInLootRoom extends BranchTask {

    private IsLootInventoryOpen islootinventoryopen;
    private EnterLootRoom enterlootroom;
    private OpiaQBD bot;

    public AreWeInLootRoom(OpiaQBD bot){
        this.bot = bot;
        enterlootroom = new EnterLootRoom(bot);
        islootinventoryopen = new IsLootInventoryOpen(bot);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return enterlootroom;
    }

    @Override
    public TreeTask successTask() {
        return islootinventoryopen;
    }
}
