package com.ethan0pia.bots.slayer.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class isItemWorthXBranch extends BranchTask {

	public isItemWorthXBranch (String args) {

	}

	private slayerMasterVisibleBranch sMaster= new slayerMasterVisibleBranch();
	private isGearedBranch geared= new isGearedBranch();

	@Override
	public TreeTask successTask() {
		return sMaster;
	}

	@Override
	public TreeTask failureTask() {
		return sMaster;
	}

	@Override
	public boolean validate() {
		return true;
	}

}