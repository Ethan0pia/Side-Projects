package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if special item needed is equipped or in bag.
 */
public class HasSpecialItem extends BranchTask {

    private GoodAssSlayerBot Bot;

    public HasSpecialItem(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private AtBankToWithdrawSpecial atbanktowithdrawspecial = new AtBankToWithdrawSpecial(Bot);
    private AtBank atbank = new AtBank(Bot);

    @Override
    public boolean validate() {

        int task = Varbits.load(7923).getValue();
        String item = Bot.mobList.getSpecialItem(task);
        Player player = Players.getLocal();

        if(player != null && item != null &&(Inventory.contains(item)|| Equipment.contains(item))){
            return true;
        }
        return false;
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
