package com.ethan0pia.bots.f2pLeveler.cowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class tanHides extends LeafTask {

    private Player player;
    private Npc Jack;

	@Override
	public void execute() {
        Jack = Npcs.newQuery().names("Jack Oval").results().nearest();
        player = Players.getLocal();
        if(Jack!=null && player!=null && !player.isMoving()) {
            Camera.concurrentlyTurnTo(Jack);
            Execution.delay(2000, 3000);
            Jack.interact("Tan hide", "Jack Oval");
            Execution.delay(1000,2000);
            if(MakeXInterface.isOpen()){
                MakeXInterface.confirm();
            }
        }
        Execution.delayUntil(() -> !Inventory.contains("Cowhide"), 3000);
    }
}