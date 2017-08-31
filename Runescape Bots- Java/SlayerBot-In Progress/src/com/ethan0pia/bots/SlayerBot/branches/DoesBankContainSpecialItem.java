package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.StopBot;

/**
 * NOTES: done
 * Checks bank for special item
 */
public class DoesBankContainSpecialItem extends BranchTask {

    private GoodAssSlayerBot Bot;

    public DoesBankContainSpecialItem(GoodAssSlayerBot bot){
        Bot=bot;
        isspecialequipable = new IsSpecialEquipable(Bot);
        stopbot = new StopBot(Bot);

    }

    private IsSpecialEquipable isspecialequipable;
    private StopBot stopbot;

    @Override
    public boolean validate() {
        int task = Varbits.load(7923).getValue();
        return Bank.contains(Bot.mobList.getSpecialItem(task));
    }

    @Override
    public TreeTask failureTask() {
        return stopbot;
    }

    @Override
    public TreeTask successTask() {
        return isspecialequipable;
    }
}
