package com.ethan0pia.bots.CowKiller.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;

public class ContainsCowhide extends BranchTask {
	

	private AtJack Jack= new AtJack();
	private AtBank bank= new AtBank();

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