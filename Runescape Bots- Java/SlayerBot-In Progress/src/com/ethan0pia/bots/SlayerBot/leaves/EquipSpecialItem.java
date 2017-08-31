package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class EquipSpecialItem extends LeafTask {

    private GoodAssSlayerBot Bot;

    public EquipSpecialItem(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        int task = Varbits.load(7923).getValue();
        String item = Bot.mobList.getSpecialItem(task);
        if(Bot.player != null){
            SpriteItem itemToEquip = Bank.newQuery().names(item).results().first();
            if (itemToEquip != null) {
                Bank.equip(itemToEquip);
            }
        }
    }
}
