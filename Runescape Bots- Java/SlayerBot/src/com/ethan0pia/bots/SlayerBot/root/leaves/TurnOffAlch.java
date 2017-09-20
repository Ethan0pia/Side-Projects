package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TurnOffAlch extends LeafTask {

    private OpiaSlayer bot;

    public TurnOffAlch(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        bot.setAlch(false);

        bot.getUtils().stuckCheck("z");
    }
}
