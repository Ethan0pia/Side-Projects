package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class SetGetFoodTrue extends LeafTask {

    private GargSlayer bot;

    public SetGetFoodTrue(GargSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        bot.setBankBool(true);
    }
}
