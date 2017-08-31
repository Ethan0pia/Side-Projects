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
import com.ethan0pia.bots.SlayerBot.leaves.WalkMob;

import java.util.List;

/**
 * NOTES: done
 * Checks if in combat while not at the mobs, but geared.
 */
public class InCombatNotAtMob extends BranchTask {

    private GoodAssSlayerBot Bot;

    public InCombatNotAtMob(GoodAssSlayerBot bot){
        Bot=bot;
        safespot = new SafeSpot(Bot);
        walkmob = new WalkMob(Bot);
    }

    private SafeSpot safespot;
    private WalkMob walkmob;

    @Override
    public boolean validate() {
        if(Bot.player!=null) {
            List<Npc> monsters = Npcs.getLoadedWithin(new Area.Circular(Bot.player.getPosition(), 30)).asList();
            if(monsters!=null) {
                for (Npc i : monsters) {
                    if (i != null) {
                        if (i.getTarget() == Bot.player) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public TreeTask failureTask() {
        return walkmob;
    }

    @Override
    public TreeTask successTask() {
        return safespot;
    }
}
