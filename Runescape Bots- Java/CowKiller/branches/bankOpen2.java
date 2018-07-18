package com.ethan0pia.bots.CowKiller.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.ethan0pia.bots.CowKiller.leaves.openBank;
import com.ethan0pia.bots.CowKiller.leaves.depositSoftLeather;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;

public class BankOpen2 extends BranchTask {
	

	private depositSoftLeather deposit= new depositSoftLeather();
	private openBank open= new openBank();

	@Override
	public TreeTask successTask() {
		return deposit;
	}

	@Override
	public TreeTask failureTask() {
		return open;
	}

	@Override
	public boolean validate() {
		//is bank open?
		return Bank.isOpen();
	}

}