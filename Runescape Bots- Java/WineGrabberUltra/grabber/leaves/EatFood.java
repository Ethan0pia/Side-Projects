package com.ethan0pia.bots.WineGrabberUltra.grabber.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EatFood extends LeafTask {


    private OpiaWineGrabberUltra bot;
    private Area safeSpot = new Area.Rectangular(new Coordinate(2967,3473,0),new Coordinate(2986,3492,0));
    private Coordinate runTo = new Coordinate(2969,3476,0);

    public EatFood(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("EatFood");
            bot.setCurrentTask("Eating food.");
            if(!bot.isRun()){
                bot.setRun(true);
            }
            ActionBar.Slot food = bot.getFood();
            if (food != null && food.isActivatable()) {
                food.activate(false);
                if(!bot.getPlayer().isMoving() && !safeSpot.contains(bot.getPlayer())){
                    BresenhamPath path = BresenhamPath.buildTo(runTo);
                    if(path!=null){
                        path.step();
                    }
                }
            } else {
                SpriteItem backupFood = Inventory.getItems(bot.getFoodType()).first();
                if (backupFood != null) {
                    backupFood.interact("Eat");
                } else {
                    bot.setBankBool(true);
                }
            }
            if(Inventory.getItems(bot.getFoodType()).isEmpty()){
                bot.setBankBool(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
