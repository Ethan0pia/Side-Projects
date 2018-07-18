package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EquipStaff extends LeafTask {

    private OpiaWineGrabberUltra bot;

    public EquipStaff(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WalkToCoords");
            bot.setCurrentTask("Equipping the staff.");
            SpriteItem staff = Inventory.newQuery().ids(1381).results().first();
            if (staff != null) {
                if (staff.click()) {
                    Execution.delayUntil(() -> Inventory.contains(1381), 200, 3000);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
