package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class WalkBank extends LeafTask {

    private GoodAssSlayerBot Bot;

    public WalkBank(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private Coordinate bankArea = new Coordinate(2888,3535,0);

    @Override
    public void execute() {
        if(Bot.player!=null) {
            if(!Inventory.contains("Ring of kinship") && !Equipment.contains("Ring of kinship")){
                final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bankArea);
                if (path != null) {
                    path.step(true);
                }
            }
            else {
                Area daemonheim = new Area.Circular(new Coordinate(3449, 3717, 0), 40);
                if (!daemonheim.contains(Bot.player)) {
                    if (Inventory.contains("Ring of kinship")) {
                        Inventory.getItems("Ring of kinship").first().interact("Teleport to Daemonheim");
                    } else if (Equipment.contains("Ring of kinship")) {
                        Equipment.getItems("Ring of kinship").first().interact("Teleport to Daemonheim");
                    } else {
                        Bot.stop();
                    }
                    Execution.delayUntil(() -> daemonheim.contains(Bot.player), 2000, 3000);
                }
                final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(new Coordinate(3449, 3717, 0));
                if (path != null) {
                    path.step(true);
                }
            }
        }
    }
}
