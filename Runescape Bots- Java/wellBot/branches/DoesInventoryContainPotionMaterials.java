package com.ethan0pia.bots.wellBot.branches;

import com.ethan0pia.bots.wellBot.leaves.OpenBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesInventoryContainPotionMaterials extends BranchTask {

    private IsMakeXWindowOpen ismakexwindowopen = new IsMakeXWindowOpen();
    private OpenBank openbank = new OpenBank();

    @Override
    public boolean validate() {
        return Inventory.isEmpty();
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
