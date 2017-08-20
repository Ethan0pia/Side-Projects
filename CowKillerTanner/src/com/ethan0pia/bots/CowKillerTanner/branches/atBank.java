package com.ethan0pia.bots.CowKillerTanner.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.location.Area.Rectangular;

import com.ethan0pia.bots.CowKillerTanner.leaves.burthropeLoadstone;

public class atBank extends BranchTask {
	

	private inventoryAlmostEmpty almostEmpty= new inventoryAlmostEmpty();
	private burthropeLoadstone offArea= new burthropeLoadstone();

	@Override
	public TreeTask successTask() {

		return almostEmpty;
	}

	@Override
	public TreeTask failureTask() {

		return offArea;
	}

	@Override
	public boolean validate() {
		//inside bank area rectangle check
		return ;
	}

}