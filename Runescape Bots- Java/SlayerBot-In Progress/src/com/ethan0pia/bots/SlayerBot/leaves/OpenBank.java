package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class OpenBank extends LeafTask {

    private GoodAssSlayerBot Bot;
    private Player player;

    public OpenBank(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        player = Players.getLocal();
        if(player!=null && !player.isMoving()){
            Bank.open();
        }
    }
}
