package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;

public class openBank extends LeafTask {

	@Override
	public void execute() {
		Bank.open();
	}

}