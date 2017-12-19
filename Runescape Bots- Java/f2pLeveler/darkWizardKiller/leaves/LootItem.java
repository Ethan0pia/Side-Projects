package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class LootItem extends LeafTask {

    @Override
    public void execute() {
        GroundItem item = GroundItems.newQuery().names("Fire rune", "Water talisman", "Cosmic rune", "Nature rune",
                "Earth talisman", "Fire talisman", "Staff", "Staff of air", "Staff of water", "Law rune").results().nearest();
        if (item != null) {
            if(item.interact("Take")){
                Execution.delayUntil(()->!item.isValid(),2000);
            }
        }
    }
}
