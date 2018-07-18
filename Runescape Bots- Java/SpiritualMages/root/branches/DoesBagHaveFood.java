package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.ethan0pia.bots.SpiritualMages.root.leaves.EatFood;
import com.ethan0pia.bots.SpiritualMages.root.leaves.SetFoodBool;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks bag for food.
 */
public class DoesBagHaveFood extends BranchTask {

    private EatFood eatfood;
    private SetFoodBool setFoodBool;
    private OpiaSpiritualMages bot;

    public DoesBagHaveFood(OpiaSpiritualMages bot){
        this.bot=bot;
        eatfood = new EatFood(bot);
        setFoodBool = new SetFoodBool(bot);
    }

    @Override
    public boolean validate() {
        return !Inventory.newQuery().actions("Eat").stacks(false).results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return setFoodBool;
    }

    @Override
    public TreeTask successTask() {
        return eatfood;
    }

}
