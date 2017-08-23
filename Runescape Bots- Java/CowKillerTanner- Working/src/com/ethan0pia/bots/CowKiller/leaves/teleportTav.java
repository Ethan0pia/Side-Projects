package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class teleportTav extends LeafTask {
    private Player player;
    @Override
    public void execute() {
        player = Players.getLocal();
        if (player != null) {
            if (!player.isMoving()) {
                Lodestone.TAVERLEY.teleport();
                Execution.delayWhile(() -> ((player = Players.getLocal()) != null && Distance.between(Lodestone.TAVERLEY.getPosition(), player) < 5), 2000, 4000);
            }
        }
    }
}
