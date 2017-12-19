package com.ethan0pia.bots.f2pLeveler.cowKiller.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.ethan0pia.bots.f2pLeveler.cowKiller.leaves.closeBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;

public class bankOpen extends BranchTask {

	private atCows atCow= new atCows();
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