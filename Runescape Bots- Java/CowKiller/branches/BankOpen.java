package com.ethan0pia.bots.CowKiller.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.ethan0pia.bots.CowKiller.leaves.closeBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;

public class BankOpen extends BranchTask {

	private AtCows atCow= new AtCows();
	private closeBank close= new closeBank();

	@Override
	public TreeTask successTask() {
		return close;
	}

	@Override
	public TreeTask failureTask() {
		return atCow;
	}

	@Override
	public boolean validate() {
		//is bank open?
		return Bank.isOpen();
	}

}