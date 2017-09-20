package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.UseQuickTeleport;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AmITargetted extends BranchTask {

    private UseQuickTeleport useQuickTeleport;
    private ItemsWorthPickingUp itemsWorthPickingUp;
    private OpiaSlayer bot;

    public AmITargetted(OpiaSlayer bot){
        this.bot=bot;
        useQuickTeleport = new UseQuickTeleport(bot);
        itemsWorthPickingUp = new ItemsWorthPickingUp(bot);
    }

    @Override
    public boolean validate() {
        Npc attacking = Npcs.newQuery().targeting(bot.getPlayer()).actions("Attack").within(new Area.Circular(bot.getPlayer().getPosition(), 15)).results().nearest();
        if(attacking!=null || bot.isGetFood()){
            return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return itemsWorthPickingUp;
    }

    @Override
    public TreeTask successTask() {
        return useQuickTeleport;
    }
}
