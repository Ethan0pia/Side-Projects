package com.ethan0pia.bots.CowKiller.branches;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.ethan0pia.bots.CowKiller.leaves.walkJack;
import com.ethan0pia.bots.CowKiller.leaves.tanHides;

public class AtJack extends BranchTask {

	private final Area jackArea= new Area.Rectangular(new Coordinate(2886,3504,0), new Coordinate(2891,3499,0));
	private Player player;

	private walkJack Jack= new walkJack();
	private tanHides tanHide= new tanHides();

	@Override
	public TreeTask successTask() {
		return tanHide;
	}

	@Override
	public TreeTask failureTask() {
		return Jack;
	}

	@Override
	public boolean validate() {
		//by jack square check
		player = Players.getLocal();
		return player !=null && jackArea.contains(player);
		}
}