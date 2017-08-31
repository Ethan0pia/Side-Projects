package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Walks to slayer master.
 */
public class WalkMaster extends LeafTask {

    private GoodAssSlayerBot Bot;

    public WalkMaster(GoodAssSlayerBot bot){
        Bot=bot;
    }

    //change coords to reflect which master
    private Player player;

    @Override
    public void execute() {
        System.out.println("fuck13!");
        player = Players.getLocal();
        if(player !=null) {
            if (!player.isMoving()) {
                final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(Bot.slayerMasterCoords);
                if (path != null) {
                    path.step(true);
                }
            }
        }
    }
}
