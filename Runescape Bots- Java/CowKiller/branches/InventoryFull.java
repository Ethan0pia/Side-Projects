package com.ethan0pia.bots.CowKiller.branches;

import com.ethan0pia.bots.CowKiller.cowKiller;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;

public class InventoryFull extends BranchTask {
	
	private cowKiller bot;
	private bankOpen bankOpn= new bankOpen();
	private containsCowhide containHide= new containsCowhide();

	@Override
	public TreeTask successTask() {
		return containHide;
	}

	@Override
	public TreeTask failureTask() {
		return bankOpn;
	}

	@Override
	public boolean validate() {
		//inventory full?
		return Inventory.getEmptySlots()<1;
	}

}