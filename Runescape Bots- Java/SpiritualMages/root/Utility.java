package com.ethan0pia.bots.SpiritualMages.root;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
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
    private Coordinate oldPosition;
    private int fails = 0;

    public Utility(OpiaSpiritualMages bot){
        this.bot=bot;
    }


    //determines if the player is stuck and attempts to fix the issue. If it gets stuck again on the same slayer task, it will stop.
    //this makes it so the bot does not remain logged in for hours while doing nothing.
    public void stuckCheck(int leaf){

        if(stuckCheck==0){
            stuckWatch.start();
        }
        if(stuckCheck==leaf && leaf!=25){
            if(!stuckWatch.isRunning()){
                stuckWatch.start();
            }
            long stuckWatchTime = stuckWatch.getRuntime();
            if(stuckWatchTime>100000 && stuckWatchTime<110000){
                Camera.turnTo(180,Random.nextDouble(0.6,0.9));
            }
            if(stuckWatchTime>600000) {
                String teleportStr = bot.getTeleport();
                if (Inventory.contains(teleportStr)) {
                    SpriteItem teleport = Inventory.getItems(teleportStr).first();
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
        Player player = bot.getPlayer();
        RegionPath regionPath = RegionPath.buildTo(finalCoords);
        if(regionPath!=null){
            if (regionPath.step()) {
                Execution.delayUntil(player::isMoving, 2000, 3000);
                if(player.isMoving()){
                    Execution.delayWhile(player::isMoving, 1000, 3000);
                }else{
                    Camera.concurrentlyTurnTo(Random.nextInt(0,360), Random.nextDouble(0.6,0.9));
                }
            } else {
                Camera.concurrentlyTurnTo(Random.nextInt(0,360), Random.nextDouble(0.6,0.9));
            }
        }else {
            WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(finalCoords);
            if (webPath != null) {
                if (webPath.step()) {
                    Execution.delayUntil(player::isMoving, 2000, 3000);
                    if(player.isMoving()){
                        Execution.delayWhile(player::isMoving, 1000, 3000);
                    }else{
                        Camera.concurrentlyTurnTo(Random.nextInt(0,360), Random.nextDouble(0.6,0.9));
                    }
                } else {
                    Camera.concurrentlyTurnTo(Random.nextInt(0,360),Random.nextDouble(0.6,0.9));
                }
            }
        }
    }
}
