package com.ethan0pia.bots.f2pLeveler.cowKiller.branches;

import com.ethan0pia.bots.f2pLeveler.cowKiller.leaves.walkBank;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;

public class atBank extends BranchTask {

	private final Area bankArea= new Area.Rectangular(new Coordinate(2886,3539,0), new Coordinate(2891,3534,0));
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
		if(player !=null && bankArea.contains(player)) {
			return true;
		} else {
			return false;
		}
	}

}