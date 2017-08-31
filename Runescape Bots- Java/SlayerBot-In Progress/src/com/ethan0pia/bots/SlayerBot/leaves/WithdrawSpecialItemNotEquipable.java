package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Withdraws item needed to kill mob if it is not equipable.
 */
public class WithdrawSpecialItemNotEquipable extends LeafTask {

    private GoodAssSlayerBot Bot;

    public WithdrawSpecialItemNotEquipable(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("fuck16!");
        int count = Varbits.load(7917).getValue();
        int task = Varbits.load(7923).getValue();
        String item = Bot.mobList.getSpecialItem(task);

        Bank.withdraw(item, count);

    }
}
