package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class walkBank extends LeafTask {

	private Area bankArea= new Area.Rectangular(new Coordinate(2884,3538,0), new Coordinate(2891,3533,0));

	@Override
	public void execute() {
		Player player = Players.getLocal();
		if(player !=null) {
			WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bankArea.getRandomCoordinate());
			if (path != null) {
				path.step();
			}
		}
	}
}