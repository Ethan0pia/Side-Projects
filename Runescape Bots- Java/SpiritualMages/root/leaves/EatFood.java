package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class EatFood extends LeafTask {

    private OpiaSpiritualMages bot;
    private int foodPrice=0;
    private ActionBar.Slot foodSlot = null;
    private SpriteItem food;

    public EatFood(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("EatFood");
        bot.setCurrentTask("Eating Food");
        try {
            if (foodSlot == null || !foodSlot.isActivatable()) {
                food = Inventory.newQuery().actions("Eat").stacks(false).results().first();
                foodSlot = null;
                if (food != null) {
                    foodSlot = ActionBar.newQuery().names(food.getDefinition().getName()).results().first();
                }
            }

            if (foodPrice == 0 && food != null) {
                int foodId = food.getId();
                foodPrice = GrandExchange.lookup(foodId).getPrice();
            }

            int health = Health.getCurrentPercent();
            if (foodSlot != null && foodSlot.isActivatable()) {
                if (foodSlot.activate(false)) {
                    Execution.delayUntil(() -> Health.getCurrentPercent() > health, 500, 800);
                }
            } else if (food != null && food.isValid()) {
                if (food.interact("Eat")) {
                    Execution.delayUntil(() -> Health.getCurrentPercent() > health, 500, 800);
                }
            }

            if (Health.getCurrentPercent() > health) {
                bot.setGpGained(bot.getGpGained() - foodPrice);
                bot.setWhenToEat(Random.nextInt(25, 70));
            }
            bot.getUtils().stuckCheck(5);
        }catch(Exception e) {
            Environment.getLogger().debug("Failed to eat food");
        }
    }

}
