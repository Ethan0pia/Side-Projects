package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.GetTask;

/**
 * NOTES: done
 * checks if player is at slayer master.
 */
public class AtMaster extends BranchTask {

    private GoodAssSlayerBot Bot;

    public AtMaster(GoodAssSlayerBot bot){
        Bot=bot;
        gettask = new GetTask(Bot);
        incombatmaster = new InCombatMaster(Bot);
    }

    private GetTask gettask;
    private InCombatMaster incombatmaster;

    @Override
    public boolean validate() {
        Area slayerMasterArea= Bot.mobList.masters(Bot.master);

        return Bot.player !=null && slayerMasterArea!=null && slayerMasterArea.contains(Bot.player);
    }

    @Override
    public TreeTask failureTask() {
        return incombatmaster;
    }

    @Override
    public TreeTask successTask() {
        return gettask;
    }
}
