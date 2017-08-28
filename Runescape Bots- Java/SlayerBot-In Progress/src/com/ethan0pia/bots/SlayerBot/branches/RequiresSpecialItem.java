package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Does the monster require a special item to kill.
 */
public class RequiresSpecialItem extends BranchTask {

    private GoodAssSlayerBot Bot;

    public RequiresSpecialItem(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private HasSpecialItem hasspecialitem = new HasSpecialItem(Bot);
    private AtBank atbank = new AtBank(Bot);

    @Override
    public boolean validate() {

        int task = Varbits.load(7923).getValue();
        if(Bot.mobList.getSpecialItem(task)==null){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public TreeTask failureTask() {
        return atbank;
    }

    @Override
    public TreeTask successTask() {
        return hasspecialitem;
    }
}
