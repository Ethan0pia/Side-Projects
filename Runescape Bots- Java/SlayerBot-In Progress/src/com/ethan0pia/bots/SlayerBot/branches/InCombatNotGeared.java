package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.SafeSpot;
import com.ethan0pia.bots.SlayerBot.leaves.WalkBank;

import java.util.List;

/**
 * NOTES: done
 * Checks if in combat if not at bank and not geared.
 */
public class InCombatNotGeared extends BranchTask {

    private GoodAssSlayerBot Bot;

    public InCombatNotGeared(GoodAssSlayerBot bot){
        Bot=bot;
        safespot = new SafeSpot(Bot);
        walkbank = new WalkBank(Bot);
    }

    private SafeSpot safespot;
    private WalkBank walkbank;

    @Override
    public boolean validate() {

        if(Bot.player!=null) {
            List<Npc> monsters = Npcs.getLoadedWithin(new Area.Circular(Bot.player.getPosition(), 20)).asList();
            if(monsters!=null) {
                for (Npc i : monsters) {
                    if (i != null) {
                        if (i.getTarget() == Bot.player) {
                            return true;
                        }
                    }
                }
            }
            if(Bot.player.getTarget()!=null){
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public TreeTask failureTask() {
        return walkbank;
    }

    @Override
    public TreeTask successTask() {
        return safespot;
    }
}
