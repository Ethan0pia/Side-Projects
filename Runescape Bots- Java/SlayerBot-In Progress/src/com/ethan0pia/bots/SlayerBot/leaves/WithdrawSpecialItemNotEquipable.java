package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Withdraws item needed to kill mob if it is not equipable.
 */
public class WithdrawSpecialItemNotEquipable extends LeafTask {

    private GoodAssSlayerBot Bot;

    public WithdrawSpecialItemNotEquipable(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {

    }
}
