package com.ethan0pia.bots.CowKiller.branches;

import com.ethan0pia.bots.CowKiller.leaves.DropMeat;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ContainsMeat extends BranchTask {
	

	private ContainsCowhide checkHides= new ContainsCowhide();
	private DropMeat dropMeat= new DropMeat();

	@Override
	public TreeTask successTask() {
		return dropMeat;
	}

	@Override
	public TreeTask failureTask() {
		return checkHides;
	}

	@Override
	public boolean validate() {
		//inventory contains cowhide?
		return Inventory.contains("Raw beef") || Inventory.contains("Bones");
	}

}