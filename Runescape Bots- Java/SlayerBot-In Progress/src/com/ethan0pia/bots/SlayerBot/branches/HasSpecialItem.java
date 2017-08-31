package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks if special item needed is equipped or in bag.
 */
public class HasSpecialItem extends BranchTask {

    private GoodAssSlayerBot Bot;

    public HasSpecialItem(GoodAssSlayerBot bot){
        Bot=bot;
        atbanktowithdrawspecial = new AtBankToWithdrawSpecial(Bot);
        atbank = new AtBank(Bot);
    }

    private AtBankToWithdrawSpecial atbanktowithdrawspecial;
    private AtBank atbank;

    @Override
    public boolean validate() {

        int task = Varbits.load(7923).getValue();
        String item = Bot.mobList.getSpecialItem(task);

        return (Bot.player != null && item != null &&(Inventory.contains(item)|| Equipment.contains(item)));

    }

    @Override
    public TreeTask failureTask() {
        return atbanktowithdrawspecial;
    }

    @Override
    public TreeTask successTask() {
        return atbank;
    }
}
