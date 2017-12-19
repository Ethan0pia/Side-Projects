package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.EatFood;
import com.ethan0pia.bots.GargoyleBot.leaves.SetGetFoodTrue;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class DoesInventoryContainFood extends BranchTask {

    private EatFood eatfood;
    private SetGetFoodTrue setgetfoodtrue;

    private GargSlayer bot;

    public DoesInventoryContainFood(GargSlayer bot){
        this.bot=bot;
        eatfood = new EatFood(bot);
        setgetfoodtrue = new SetGetFoodTrue(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.contains(bot.getFoodType());
    }

    @Override
    public TreeTask failureTask() {
        return setgetfoodtrue;
    }

    @Override
    public TreeTask successTask() {
        return eatfood;
    }
}
