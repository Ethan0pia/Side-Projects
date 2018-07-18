package com.ethan0pia.bots.TrollKiller.leaves;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.rs3.local.hud.interfaces.LootInventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class LootStaff extends LeafTask {

    private TrollKiller bot;

    public LootStaff(TrollKiller bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            if (LootInventory.isOpen()) {
                if (LootInventory.contains(1381)) {
                    if (LootInventory.take("Staff of air")) {
                        Execution.delayUntil(() -> Inventory.contains(1381), 2000);
                        if (Inventory.contains(1381)) {
                            LootInventory.close();
                        }
                    }
                } else {
                    LootInventory.close();
                }
            } else {
                GroundItem staff = GroundItems.newQuery().names("Staff of air").results().nearest();
                if (staff != null) {
                    staff.interact("Take");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
