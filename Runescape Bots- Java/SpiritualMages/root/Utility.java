package com.ethan0pia.bots.SlayerBot.root;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;

public class Utility {

    private OpiaSpiritualMages bot;
    private int stuckCheck=0;
    private StopWatch stuckWatch = new StopWatch();

    public Utility(OpiaSpiritualMages bot){
        this.bot=bot;
    }


    //determines if the player is stuck and attempts to fix the issue. If it gets stuck again on the same slayer task, it will stop.
    //this makes it so the bot does not remain logged in for hours while doing nothing.
    public void stuckCheck(int leaf){

        if(stuckCheck==0){
            stuckWatch.start();
        }
        if(stuckCheck==leaf){
            if(!stuckWatch.isRunning()){
                stuckWatch.start();
            }
            if(stuckWatch.getRuntime()>100000 && stuckWatch.getRuntime()<110000){
                Camera.turnTo(180,0.666);
            }
            if(stuckWatch.getRuntime()>600000) {
                if (Inventory.contains(8010)) {
                    SpriteItem teleport = Inventory.newQuery().ids(8010).results().first();
                    teleport.click();
                }
                Environment.getLogger().debug("Bot failed in " + leaf);
                Environment.getLogger().severe("The bot got stuck on a task for more than 10 minutes.");
                bot.stop("FAILED");
            }
        }else{
            stuckCheck=leaf;
            stuckWatch.reset();
            if(!stuckWatch.isRunning()){
                stuckWatch.start();
            }
        }
    }

    public void walkPath(Coordinate finalCoords){
        RegionPath regionPath = RegionPath.buildTo(finalCoords);
        if(regionPath!=null){
            if (regionPath.step()) {
                Execution.delayUntil(() -> !bot.getPlayer().isMoving(), 1000, 3000);
            } else {
                Camera.concurrentlyTurnTo(regionPath.getNext(), Random.nextDouble(0.6, 0.666));
            }
        }else {
            WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(finalCoords);
            if (webPath != null) {
                if (webPath.step()) {
                    Execution.delayUntil(() -> !bot.getPlayer().isMoving(), 1000, 3000);
                } else {
                    Camera.concurrentlyTurnTo(webPath.getNext(), Random.nextDouble(0.6, 0.666));
                }
            }
        }
    }
}
