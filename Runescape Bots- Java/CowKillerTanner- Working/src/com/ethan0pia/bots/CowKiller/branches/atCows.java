package com.ethan0pia.bots.CowKiller.branches;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;

public class atCows extends BranchTask {
	
	private Coordinate cowArea = new Coordinate(2885,3487,0);
	private Player player;

	private atTavLoad tavLoad= new atTavLoad();
	private checkGround ground= new checkGround();

	@Override
	public TreeTask successTask() {
		return ground;
	}

	@Override
	public TreeTask failureTask() {
		return tavLoad;
	}

	@Override
	public boolean validate() {
		//inside cow area rectangle check
		player = Players.getLocal();
		if(player != null) {
			if (player.distanceTo(cowArea) < 7) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}