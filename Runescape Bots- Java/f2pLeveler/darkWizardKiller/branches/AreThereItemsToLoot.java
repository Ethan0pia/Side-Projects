package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.LootItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreThereItemsToLoot extends BranchTask {

    private LootItem lootitem = new LootItem();
    private AmIAlreadyInCombat amialreadyincombat = new AmIAlreadyInCombat();

    @Override
    public boolean validate() {
        return !GroundItems.newQuery().names("Fire rune","Water talisman","Cosmic rune", "Nature rune",
                "Earth talisman", "Fire talisman", "Staff","Staff of air","Staff of water","Law rune").results().isEmpty() && !Inventory.isFull();
    }

    @Override
    public TreeTask failureTask() {
        return amialreadyincombat;
    }

    @Override
    public TreeTask successTask() {
        return lootitem;
    }
}
