package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WithdrawRingOfKinship extends LeafTask {

    private GoodAssSlayerBot Bot;

    public WithdrawRingOfKinship(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {

        System.out.println("fuck14!");
        Bank.withdraw(15707, 1);
    }
}
