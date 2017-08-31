package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Finds a safe spot and runs to it.
 */
public class SafeSpot extends LeafTask {

    private GoodAssSlayerBot Bot;

    public SafeSpot(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        if(Inventory.contains("Ring of kinship")){
            Inventory.getItems("Ring of kinship").first().interact("Teleport to Daemonheim");
        }
        else if(Equipment.contains("Ring of kinship")){
            Equipment.getItems("Ring of kinship").first().interact("Teleport to Daemonheim");
        }
        else{
            Bot.stop();
        }

    }
}
