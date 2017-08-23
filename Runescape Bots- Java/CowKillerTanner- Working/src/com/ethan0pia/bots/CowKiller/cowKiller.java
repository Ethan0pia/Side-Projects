package com.ethan0pia.bots.CowKiller;
import com.ethan0pia.bots.CowKiller.branches.inventoryFull;

import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class cowKiller extends TreeBot {

	@Override
	public void onStart(String... args) {
		setLoopDelay(1000, 1800);
	}
	
	@Override
	public TreeTask createRootTask() {

		return new inventoryFull();
	}
}