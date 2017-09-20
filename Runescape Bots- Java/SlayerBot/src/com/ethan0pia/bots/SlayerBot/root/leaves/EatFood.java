package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class EatFood extends LeafTask {
    private OpiaSlayer bot;

    public EatFood(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        String food = bot.getMonster().getFoodType();
        if(bot.getPlayer()!=null && Inventory.contains(food)) {
            int health = Health.getCurrentPercent();
            Inventory.getItems(food).last().interact("Eat");
            Execution.delayUntil(()->Health.getCurrentPercent()>health,500,1200);
            if(Health.getCurrentPercent()>health){
                bot.setWhenToEat(Random.nextInt(25, 70));
            }
        }
        bot.getUtils().stuckCheck("t");
    }
}
