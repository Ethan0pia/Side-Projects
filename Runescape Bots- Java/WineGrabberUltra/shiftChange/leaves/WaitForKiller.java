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
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WaitForKiller extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private Coordinate end = new Coordinate(3045,3520,0);
    private Coordinate oldCoords = null;
    private int pathFails;

    public WaitForKiller(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WaitForKiller");
            bot.setCurrentTask("Waiting for killer.");
            Area.Circular playerArea = new Area.Circular(bot.getPlayer().getPosition(),5);
            Execution.delayWhile(()-> Players.newQuery().within(playerArea).filter(i->i.getName().equals(bot.getKiller())).results().isEmpty(),1000,240000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
