package com.ethan0pia.bots.CowKiller.branches;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.ethan0pia.bots.CowKiller.leaves.walkCows;

public class AtCows extends BranchTask {

	private Player player;

	private walkCows tavLoad= new walkCows();
	private CheckGround ground= new CheckGround();

	private final Area cowArea= new Area.Rectangular(new Coordinate(2877,3497,0), new Coordinate(2892,3479,0));

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
		if(player != null && cowArea.contains(player)) {
				return true;
		} else {
				return false;
		}
	}

}