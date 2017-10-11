package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class EatFood extends LeafTask {
    private OpiaSpiritualMages bot;
    private int foodPrice=0;

    public EatFood(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        String food = "Beltfish";
        if(Inventory.contains(food)) {
            int health = Health.getCurrentPercent();
            SpriteItem foodToEat = Inventory.getItems(food).last();
            if(foodPrice==0){
                int foodId=foodToEat.getId();
                foodPrice = GrandExchange.lookup(foodId).getPrice();
            }
            foodToEat.interact("Eat");
            Execution.delayUntil(()->Health.getCurrentPercent()>health,500,800);
            if(Health.getCurrentPercent()>health){
                bot.setGpGained(bot.getGpGained()-foodPrice);
                bot.setWhenToEat(Random.nextInt(25, 70));
            }
        }
        bot.getUtils().stuckCheck(21);
    }
}
