package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class walkJack extends LeafTask {

    private Coordinate jackCoords;
	private final Area jackArea= new Area.Rectangular(new Coordinate(2886,3503,0), new Coordinate(2890,3499,0));
    private Player player;

	@Override
	public void execute() {
		player = Players.getLocal();
		jackCoords = jackArea.getRandomCoordinate();
		if (player!=null && jackCoords!=null) {
			WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(jackCoords);
			if (path != null) {
				path.step();
			}
		}
	}
}