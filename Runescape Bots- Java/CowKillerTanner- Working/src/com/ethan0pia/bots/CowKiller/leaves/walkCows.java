package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.entities.Player;

public class walkCows extends LeafTask {

	private Player player;
	private Coordinate cowArea = new Coordinate(2885,3487,0);

	@Override
	public void execute() {
		player = Players.getLocal();
		if (!player.isMoving()) {
			final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(cowArea);
			if (path != null) {
				path.step(true);
			}
		}
	}

}