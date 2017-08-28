package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.region.Players;

public class openBank extends LeafTask {

	private Player player;
	private Npc banker;

	@Override
	public void execute() {
		banker = Npcs.getLoaded("Gnome Banker").sortByDistance().nearest();
		if(banker!=null) {
			Camera.concurrentlyTurnTo(banker);
		}
		player = Players.getLocal();
		if(player != null && !player.isMoving()) {
			Bank.open();
		}
	}

}