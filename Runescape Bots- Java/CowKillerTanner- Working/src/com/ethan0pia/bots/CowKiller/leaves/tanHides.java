package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
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
            if(!Jack.interact("Tan hide", "Jack Oval")){
                Keyboard.pressKey(27);
            }
            Execution.delayUntil(() -> !Inventory.contains("Cowhide"), 1000, 4000);
        }
	}

}