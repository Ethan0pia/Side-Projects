package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.SafeSpot;
import com.ethan0pia.bots.SlayerBot.leaves.WalkMaster;

import java.util.List;

/**
 * NOTES: done
 * Checks if in combat after checking if at slayer master.
 */
public class InCombatMaster extends BranchTask {

    private GoodAssSlayerBot Bot;

    public InCombatMaster(GoodAssSlayerBot bot){
        Bot=bot;
        safespot = new SafeSpot(Bot);
        walkmaster = new WalkMaster(Bot);
    }

    private SafeSpot safespot;
    private WalkMaster walkmaster;

    @Override
    public boolean validate() {
        if(Bot.player!=null) {
            if(Bot.player.getAdrenalineGauge()!=null){
                return true;
            }
            List<Npc> monsters = Npcs.getLoadedWithin(new Area.Circular(Bot.player.getPosition(), 30)).asList();
            for (Npc i : monsters) {
                if(i!=null) {
                    if (i.getTarget() == Bot.player) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public TreeTask failureTask() {
        return walkmaster;
    }

    @Override
    public TreeTask successTask() {
        return safespot;
    }
}
