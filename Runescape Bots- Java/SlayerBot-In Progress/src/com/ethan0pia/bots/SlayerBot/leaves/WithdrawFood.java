package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WithdrawFood extends LeafTask {

    private GoodAssSlayerBot Bot;

    public WithdrawFood(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("fuck13!");
        Bank.contains("Bot.food");
        if(!Bank.contains(Bot.food)){
            System.out.println("Correct food not in bank.");
            Bot.stop();
        }

        Bank.withdraw(Bot.food, 10);
    }
}
