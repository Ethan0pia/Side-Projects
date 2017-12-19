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
        if(!bot.getDuelWatch().isRunning()){
            bot.getDuelWatch().stop();
            bot.getDuelWatch().reset();
        }
        Player opponent = bot.getOpponent();
        if (opponent.getPosition() == bot.getPlayer().getPosition()) {
            RegionPath path = RegionPath.buildTo(duelingLobbyCenter.getRandomCoordinate());
            if (path != null) {
                path.step();
            }
        }

        if (!bot.isMaster()) {
            if (!opponent.interact("Challenge")) {
                Camera.concurrentlyTurnTo(opponent);
            } else {
                Execution.delayUntil(this::validation, 5000);
            }
        }else if (bot.isSendChallenge()) {
            if (!opponent.interact("Challenge")) {
                Camera.concurrentlyTurnTo(opponent);
            } else {
                Execution.delayUntil(()->validation() || validateSecondInterface(), 5000);
                bot.setSendChallenge(false);
            }
        }
    }

    private boolean validation(){
        InterfaceComponent firstScreen = Interfaces.newQuery().containers(1369).results().first();
        try {
            //will throw null pointer if second interface is null
            return firstScreen != null || Interfaces.newQuery().containers(1367).textContains("Accept").results().last().isVisible();
        }catch(Exception e){
            return false;
        }
    }

    private boolean validateSecondInterface(){
        try {
            //will return null if not correct.
            return Interfaces.newQuery().containers(1367).textContains("Accept").results().last().isVisible();
        }catch(Exception e) {
            return false;
        }
    }
}
