package com.ethan0pia.bots.WineGrabberUltra.shiftChange.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ChangeShift extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private Coordinate end = new Coordinate(3045,3520,0);
    private Coordinate oldCoords = null;
    private int pathFails;

    public ChangeShift(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("ChangeShift");
            bot.setCurrentTask("Changing shifts.");
            Player player = bot.getPlayer();
            if(!player.getPosition().equals(end)){
                if(Bank.isOpen()){
                    Bank.close();
                }else if(end.isVisible()){
                    end.interact("Walk here");
                }else {
                    WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(end);
                    if (path != null) {
                        if (oldCoords == null) {
                            oldCoords = player.getPosition();
                        }
                        if (!path.step()) {
                            Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(0.5,0.85));
                        }
                        if (!oldCoords.equals(player.getPosition())) {
                            Environment.getLogger().debug("Moved");
                            pathFails = 0;
                            oldCoords = player.getPosition();
                        } else {
                            pathFails++;
                            Environment.getLogger().debug("failed to move " + pathFails);
                            if (pathFails > 2) {
                                Environment.getLogger().debug("Using alternative path to get to bank");
                                BresenhamPath pathBackup = BresenhamPath.buildTo(end);
                                if (pathBackup != null) {
                                    pathBackup.step();
                                    if (pathFails > 4) {
                                        Environment.getLogger().debug("Using viewport path to get to bank");
                                        ViewportPath.convert(pathBackup).step();
                                    }
                                }
                            }
                        }
                    } else {
                        Environment.getLogger().debug("Using alternative path to get to bank");
                        BresenhamPath pathBackup = BresenhamPath.buildTo(end);
                        if (pathBackup != null) {
                            pathBackup.step();
                        }
                    }
                }
            }else {
                if (bot.isAlias2()) {
                    Environment.getLogger().debug("No other shift account selected!");
                    bot.stop("No other shift account selected!");
                }
                if (Bank.isOpen()) {
                    Bank.close();
                } else if (bot.getPlayer() != null && bot.getPlayer().getTarget() == null) {
                    GameEvents.Universal.LOGIN_HANDLER.disable();
                    GameEvents.Universal.INTERFACE_CLOSER.disable();
                    if (Keyboard.typeKey(27)) {
                        InterfaceComponent exit = Interfaces.newQuery().textContains("Logout").results().first();
                        if (exit != null && exit.isVisible() && exit.click()) {
                            bot.swapAndResetAlias();
                            if(!Environment.setAccount(bot.currentAlias())){
                                ClientUI.showAlert("Failed to switch to the next shift!");
                                bot.stop("Failed to switch aliases");
                            }
                            GameEvents.Universal.LOGIN_HANDLER.enable();
                            GameEvents.Universal.INTERFACE_CLOSER.enable();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
