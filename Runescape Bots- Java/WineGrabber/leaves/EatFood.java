package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EatFood extends LeafTask {


    private boolean checked = false;
    private ActionBar.Slot food = null;
    private OpiaWineGrabber bot;

    public EatFood(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            if (food == null && !checked) {
                SpriteItem inventoryFood = Inventory.newQuery().actions("Eat").results().first();
                if (inventoryFood != null) {
                    String foodName = inventoryFood.getDefinition().getName();
                    food = ActionBar.newQuery().names(foodName).results().first();
                    checked = true;
                }
            }

            if (food != null && food.isActivatable()) {
                food.activate(false);
            } else {
                SpriteItem backupFood = Inventory.newQuery().actions("Eat").results().first();
                if (backupFood != null) {
                    backupFood.interact("Eat");
                } else {
                    bot.setBankBool(true);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
