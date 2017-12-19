package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class SetFoodBool extends LeafTask {

    private OpiaSlayer bot;

    public SetFoodBool(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        bot.setGetFood(true);
        bot.getUtils().stuckCheck(25);
    }
}
