package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;

public class openBank extends LeafTask {
	private Npc banker;
	@Override
	public void execute() {
		banker = Npcs.getLoaded("Gnome Banker").sortByDistance().nearest();
		if(banker!=null) {
			Camera.concurrentlyTurnTo(banker);
		}
		Bank.open();
	}

}