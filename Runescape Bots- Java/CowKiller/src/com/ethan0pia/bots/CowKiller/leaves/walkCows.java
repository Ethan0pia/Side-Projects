package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.entities.Player;

public class walkCows extends LeafTask {

	private Player player;
	private Coordinate cowCoords;
	private final Area cowArea= new Area.Rectangular(new Coordinate(2882,3491,0), new Coordinate(2889,3483,0));

	@Override
	public void execute() {
		cowCoords = cowArea.getRandomCoordinate();
		player = Players.getLocal();
		if (player!=null && cowCoords!=null) {
			WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(cowCoords);
			if (path != null) {
				path.step();
			}
		}
	}

}