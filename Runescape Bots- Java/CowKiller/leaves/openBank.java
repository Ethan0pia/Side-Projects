package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.region.Players;

public class openBank extends LeafTask {

	@Override
	public void execute() {
		Player player = Players.getLocal();
		if(player != null && !player.isMoving()) {
			Bank.open();
		}
	}
}