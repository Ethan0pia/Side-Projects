package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EatFood extends LeafTask {

    private GoodAssSlayerBot Bot;

    public EatFood(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        Player player = Players.getLocal();
        if(player!=null) {
            Inventory.getItems(Bot.food).first().interact("Eat");
        }
    }
}
