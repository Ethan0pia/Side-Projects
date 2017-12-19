package com.ethan0pia.bots.wellBot.branches;

import com.ethan0pia.bots.wellBot.leaves.WithdrawPreset;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class IsBankOpen extends BranchTask {

    private WithdrawPreset withdrawpreset = new WithdrawPreset();
    private DoesInventoryContainPotionMaterials doesinventorycontainpotionmaterials = new DoesInventoryContainPotionMaterials();

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return doesinventorycontainpotionmaterials;
    }

    @Override
    public TreeTask successTask() {
        return withdrawpreset;
    }
}
