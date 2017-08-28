package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Stops the bot.
 */
public class StopBot extends LeafTask {

    private GoodAssSlayerBot Bot;

    public StopBot(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {

    }
}
