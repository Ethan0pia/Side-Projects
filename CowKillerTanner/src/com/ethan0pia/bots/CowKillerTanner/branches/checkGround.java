package com.ethan0pia.bots.CowKillerTanner.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.location.Area.Rectangular;

public class atCows extends BranchTask {
	

	private atJack jack= new atJack();
	private isInventoryFull full= new isInventoryFull();

	@Override
	public TreeTask successTask() {

		return full;
	}

	@Override
	public TreeTask failureTask() {

		return jack;
	}

	@Override
	public boolean validate() {
		

		return ;
	}

}