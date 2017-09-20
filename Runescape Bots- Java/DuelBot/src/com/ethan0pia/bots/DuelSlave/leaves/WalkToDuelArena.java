package com.ethan0pia.bots.DuelSlave.leaves;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToDuelArena extends LeafTask {

    private Area duelingLobby = new Area.Rectangular(new Coordinate(3331,3241,0), new Coordinate(3351,3223,0));
    private DuelingSlave bot;
    public WalkToDuelArena(DuelingSlave bot){
        this.bot=bot;
    }



    @Override
    public void execute() {
        Player player = bot.getPlayer();
        if(player!=null)
        if (!new Area.Circular(duelingLobby.getPosition(), 8).contains(player)) {
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(duelingLobby.getRandomCoordinate());
            if (path != null) {
                path.step();
            }
        }
    }
}
