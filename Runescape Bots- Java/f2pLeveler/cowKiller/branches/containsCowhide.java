package com.ethan0pia.bots.f2pLeveler.cowKiller.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;

public class containsCowhide extends BranchTask {
	

	private atJack Jack= new atJack();
	private atBank bank= new atBank();

	@Override
	public TreeTask successTask() {
		return Jack;
	}

	@Override
	public TreeTask failureTask() {
		return bank;
	}

	@Override
	public boolean validate() {
		//inventory contains cowhide?
		return Inventory.contains("Cowhide");
	}

}