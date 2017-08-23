package com.ethan0pia.bots.SlayerBot;
import com.ethan0pia.bots.SlayerBot.branches.HaveTask;

import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class GoodAssSlayerBot extends TreeBot {

	public static Coordinate getSlayerMasterCoords() {
		return slayerMasterCoords;
	}

	public void setSlayerMasterCoords(Coordinate slayerMasterCoords) {
		this.slayerMasterCoords = slayerMasterCoords;
	}

	public static Coordinate slayerMasterCoords= new Coordinate(2911,3422,0);

	@Override
	public void onStart(String... args) {
		setLoopDelay(1000, 1800);
	}

	@Override
	public TreeTask createRootTask() {

		return new HaveTask();
	}
}