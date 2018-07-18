package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
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
            Environment.getLogger().debug("EatFood");
            if(!bot.isRun()){
                bot.setRun(true);
            }
            if (food == null && !checked) {
                food = ActionBar.newQuery().names(bot.getFoodType()).results().first();
                checked = true;
            }
            if (food != null && food.isActivatable()) {
                food.activate(false);
            } else {
                SpriteItem backupFood = Inventory.getItems(bot.getFoodType()).first();
                if (backupFood != null) {
                    backupFood.interact("Eat");
                } else {
                    bot.setBankBool(true);
                }
            }
            if((food == null || !food.isActivatable()) && Inventory.getItems(bot.getFoodType()).first()==null){
                bot.setBankBool(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
