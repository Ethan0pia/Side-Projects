package com.ethan0pia.bots.CowKiller.branches;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.ethan0pia.bots.CowKiller.leaves.walkBank;

public class atBank extends BranchTask {
	
	private Coordinate bankArea = new Coordinate(2888,3535,0);
	private Player player;

	private walkBank atBurth= new walkBank();
	private bankOpen2 bank= new bankOpen2();

	@Override
	public TreeTask successTask() {
		return bank;
	}

	@Override
	public TreeTask failureTask() {
		return atBurth;
	}

	@Override
	public boolean validate() {
		//inside bank area
		player = Players.getLocal();
		if(player !=null) {
			if (player.distanceTo(bankArea) < 5) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}