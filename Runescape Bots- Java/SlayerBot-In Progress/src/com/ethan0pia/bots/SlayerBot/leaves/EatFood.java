package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class EatFood extends LeafTask {

    private GoodAssSlayerBot Bot;

    public EatFood(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        if(Bot.player!=null) {
            System.out.println("Eating food at " + Health.getCurrentPercent()+"%");
            Inventory.getItems(Bot.food).first().interact("Eat");
        }
    }
}
