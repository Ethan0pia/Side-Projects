package com.ethan0pia.bots.DuelSlave.leaves;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ClickDuelOnPlayer extends LeafTask{

    private DuelingSlave bot;
    private Area duelingLobby = new Area.Rectangular(new Coordinate(3331,3241,0), new Coordinate(3351,3223,0));
    private Area duelingLobbyCenter = new Area.Circular(duelingLobby.getCenter(),4);

    public ClickDuelOnPlayer(DuelingSlave bot){
        this.bot=bot;
    }

    @Override
    public void execute() {

        Player player = bot.getPlayer();
        InterfaceComponent firstScreen = Interfaces.newQuery().filter(i -> i.getId() == 1369).results().first();
        InterfaceComponent secondScreen = Interfaces.newQuery().filter(i -> i.getId() == 1367).results().first();
        Player opponent = Players.newQuery().names(bot.getOpponentsName()).results().first();
        if (opponent != null) {
            if (opponent.getPosition() == player.getPosition()) {
                RegionPath path = RegionPath.buildTo(duelingLobbyCenter.getRandomCoordinate());
                if (path != null) {
                    path.step();
                }
            }

            if (bot.getDuelTimer() != null && !bot.getDuelTimer().isRunning()) {
                bot.getDuelTimer().start();
            }
            if (bot.getDuelTimer() != null && bot.getDuelTimer().getRuntime() > 25000) {
                bot.setSentChallenge(false);
                bot.setSendChallenge(false);
                bot.getDuelTimer().reset();
            }

            if (!bot.isMaster()) {
                if (!bot.isSentChallenge()) {
                    if (!opponent.interact("Challenge")) {
                        Camera.concurrentlyTurnTo(opponent);
                    } else {
                        Execution.delayUntil(() -> firstScreen != null || secondScreen != null, 800, 1200);
                    }
                }
            } else if (bot.isSendChallenge()) {
                if (!opponent.interact("Challenge")) {
                    Camera.concurrentlyTurnTo(opponent);
                } else {
                    Execution.delayUntil(() -> firstScreen != null || secondScreen != null, 800, 1200);
                }
            }
        }
    }
}
