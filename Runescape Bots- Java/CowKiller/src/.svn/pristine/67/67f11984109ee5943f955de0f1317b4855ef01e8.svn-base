package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class walkBank extends LeafTask {

    private Coordinate bankCoords;
	private final Area bankArea= new Area.Rectangular(new Coordinate(2887,3537,0), new Coordinate(2891,3534,0));
    private Player player;
	@Override
	public void execute() {
		player = Players.getLocal();
		bankCoords = bankArea.getRandomCoordinate();
		if(player !=null && bankCoords!=null) {
			final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bankCoords);
			if (path != null) {
				path.step(true);
			}
		}
	}

}