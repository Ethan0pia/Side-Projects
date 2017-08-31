package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EmptyInventory extends LeafTask {

    private GoodAssSlayerBot Bot;

    public EmptyInventory(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        Bank.depositInventory();
    }
}
