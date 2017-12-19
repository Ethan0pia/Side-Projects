package com.ethan0pia.bots.tanBot.branches;

import com.ethan0pia.bots.tanBot.leaves.OpenBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesInventoryContainHides extends BranchTask {

    private IsMakeXWindowOpen ismakexwindowopen = new IsMakeXWindowOpen();
    private OpenBank openbank = new OpenBank();

    @Override
    public boolean validate() {
        return !Inventory.newQuery().ids(1739,1747,1749,1751,1753,6287,24372).results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return openbank;
    }

    @Override
    public TreeTask successTask() {
        return ismakexwindowopen;
    }
}
