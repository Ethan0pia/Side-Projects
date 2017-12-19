package com.ethan0pia.bots.f2pLeveler.cowKiller.leaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class depositSoftLeather extends LeafTask {
	@Override
	public void execute() {
		Bank.depositInventory();
	}

}