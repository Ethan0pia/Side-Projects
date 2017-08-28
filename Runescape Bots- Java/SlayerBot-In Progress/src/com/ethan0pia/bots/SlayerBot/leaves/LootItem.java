package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Turn camera and loot item.
 */
public class LootItem extends LeafTask {

    private GoodAssSlayerBot Bot;

    public LootItem(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {

    }
}
