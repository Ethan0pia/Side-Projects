package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Deposits one food to make room for special item.
 */
public class DepositOneFood extends LeafTask {

    private GoodAssSlayerBot Bot;

    public DepositOneFood(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        Bank.deposit(Bot.food, 1);
    }
}
