package com.ethan0pia.bots.f2pLeveler.cowKiller.leaves;

import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;

public class closeBank extends LeafTask {

	@Override
	public void execute() {
		Bank.close();
	}

}