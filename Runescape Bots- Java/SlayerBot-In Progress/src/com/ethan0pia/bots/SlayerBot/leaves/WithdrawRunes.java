package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WithdrawRunes extends LeafTask {

    private GoodAssSlayerBot Bot;

    public WithdrawRunes(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("fuck15!");
        Bank.withdraw(556, 1000);
    }
}
