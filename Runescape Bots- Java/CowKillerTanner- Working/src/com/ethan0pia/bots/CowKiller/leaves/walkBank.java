package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class walkBank extends LeafTask {

    private Coordinate bankArea = new Coordinate(2888,3535,0);
    private Player player;
	@Override
	public void execute() {
		player = Players.getLocal();
		if(player !=null) {
			if (!player.isMoving()) {
				final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bankArea);
				if (path != null) {
					path.step(true);
				}
			}
		}
	}

}