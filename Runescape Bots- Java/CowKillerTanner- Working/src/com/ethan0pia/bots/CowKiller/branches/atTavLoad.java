package com.ethan0pia.bots.CowKiller.branches;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.region.Players;
import com.ethan0pia.bots.CowKiller.leaves.walkCows;
import com.ethan0pia.bots.CowKiller.leaves.teleportTav;

public class atTavLoad extends BranchTask {

    private Player player;

    private walkCows walk = new walkCows();
    private teleportTav teleport = new teleportTav();

    @Override
    public TreeTask successTask() {
        return walk;
    }

    @Override
    public TreeTask failureTask() {
        return teleport;
    }

    @Override
    public boolean validate() {
        //inside tav loadstone area
        player = Players.getLocal();
        if(player !=null) {
            if (player.distanceTo(Lodestone.TAVERLEY.getPosition()) < 80) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}