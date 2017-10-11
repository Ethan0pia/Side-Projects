package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class SetFoodBool extends LeafTask {

    private OpiaSpiritualMages bot;

    public SetFoodBool(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        bot.setGetFood(true);
        bot.getUtils().stuckCheck(25);
    }
}
