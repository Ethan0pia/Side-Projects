package com.ethan0pia.bots.telegrabLeveler.leaves;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EquipStaff extends LeafTask {

    private TelegrabLeveler bot;

    public EquipStaff(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            SpriteItem staff = Inventory.newQuery().ids(1381).results().first();
            if (staff != null) {
                if (staff.click()) {
                    Execution.delayUntil(() -> Inventory.contains(1381), 1000);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
