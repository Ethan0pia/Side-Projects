package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.GetTask;

/**
 * NOTES: done
 * Check if at the slayer master
 */
public class AmIAtMaster extends BranchTask {

    private GetTask gettask;
    private IsMasterChaeldar isMasterChaeldar;
    private OpiaSlayer bot;

    public AmIAtMaster(OpiaSlayer bot){
        this.bot=bot;
        gettask = new GetTask(bot);
        isMasterChaeldar = new IsMasterChaeldar(bot);
    }

    @Override
    public boolean validate() {
        Npc master = Npcs.newQuery().actions("Get-task").within(new Area.Circular(bot.getPlayer().getPosition(),9)).results().nearest();
        return master !=null;
    }

    @Override
    public TreeTask failureTask() {
        return isMasterChaeldar;
    }

    @Override
    public TreeTask successTask() {
        return gettask;
    }
}
