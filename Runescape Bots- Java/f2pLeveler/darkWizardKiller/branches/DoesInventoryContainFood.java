package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class DoesInventoryContainFood extends BranchTask {

    private IsHealthLow isHealthLow = new IsHealthLow();
    private AmIAtBank amiatbank = new AmIAtBank();

    @Override
    public boolean validate() {
        return !Inventory.newQuery().actions("Eat").results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return amiatbank;
    }

    @Override
    public TreeTask successTask() {
        return isHealthLow;
    }
}
