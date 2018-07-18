package com.ethan0pia.bots.WineGrabberUltra.grabber.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import static com.runemate.game.api.hybrid.input.Mouse.Button.LEFT;

/**
 * NOTES:
 * 
 */
public class RunToTelegrabSpot extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private int pathFails = 0;
    private Coordinate oldCoords;
    private Coordinate spot;
    private Area.Circular churchArea;

    public RunToTelegrabSpot(OpiaWineGrabberUltra bot){
        this.bot=bot;
        spot = bot.getTelegrabSpot();
        if(spot!=null){
            churchArea = new Area.Circular(spot,30);
        }
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("RunToTelegrabSpot");
            bot.setCurrentTask("Running to church.");
            if(spot==null || churchArea==null) {
                spot = bot.getTelegrabSpot();
                churchArea = new Area.Circular(spot,30);
            } else {
                Player player = bot.getPlayer();
                checkAbilities();
                if (spot.isVisible()) {
                    if ((Camera.getYaw() < bot.getCameraYaw()-2 || Camera.getYaw() > bot.getCameraYaw()+2) || Camera.getPitch()<bot.getCameraPitch()-0.05 || Camera.getPitch()>bot.getCameraPitch()+0.05) {
                        Camera.concurrentlyTurnTo(bot.getCameraYaw(), bot.getCameraPitch(), 0.1);
                    }
                    if(player.isMoving()){
                        Execution.delayWhile(player::isMoving,100,3000);
                    }
                    if (spot.interact("Walk here")) {
                        move(player);
                    } else {
                        if (spot.click()) {
                            move(player);
                        }
                    }
                } else {
                    WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(spot);
                    if (path != null) {
                        if(oldCoords==null){
                            oldCoords = player.getPosition();
                        }
                        if(!path.step() && !churchArea.contains(player)){
                            Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(0.5,0.85));
                        }
                        if(!oldCoords.equals(player.getPosition())){
                            Environment.getLogger().debug("Moved");
                            pathFails=0;
                            oldCoords = player.getPosition();
                        }else{
                            pathFails++;
                            Environment.getLogger().debug("failed to move "+ pathFails + " times");
                            if(pathFails > 2) {
                                Environment.getLogger().debug("Using alternative path to get to telegrab spot");
                                BresenhamPath pathBackup = BresenhamPath.buildTo(spot);
                                if(pathBackup!=null) {
                                    if(!pathBackup.step()&& !churchArea.contains(player)){
                                        Camera.concurrentlyTurnTo(path.getNext(), Random.nextDouble(0.5,0.85));
                                    }
                                    if(pathFails>4){
                                        Environment.getLogger().debug("Using viewport path to get to telegrab spot");
                                        ViewportPath.convert(pathBackup).step();
                                    }
                                }
                            }
                        }
                    } else {
                        BresenhamPath pathBackup = BresenhamPath.buildTo(spot);
                        if(pathBackup!=null) {
                            if (oldCoords == null) {
                                oldCoords = player.getPosition();
                            }
                            if(!pathBackup.step()&& !churchArea.contains(player)){
                                Camera.concurrentlyTurnTo(pathBackup.getNext(), Random.nextDouble(0.5,0.85));
                            }
                            if (!oldCoords.equals(player.getPosition())) {
                                Environment.getLogger().debug("Moved");
                                pathFails = 0;
                                oldCoords = player.getPosition();
                            } else {
                                pathFails++;
                                Environment.getLogger().debug("failed to move " + pathFails + " times");
                                if (pathFails > 4) {
                                    Environment.getLogger().debug("Using viewport path to get to telegrab spot");
                                    ViewportPath.convert(pathBackup).step();
                                }
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void move(Player player){
        Execution.delayUntil(() -> player.isMoving() || player.getHealthGauge() != null, 100,1000);
        Execution.delayWhile(() -> player.isMoving() && Health.getCurrentPercent() > 45, 250,1500);
    }

    private void checkAbilities(){
        if(!bot.isChecked()){
            bot.setTelegrab(ActionBar.newQuery().names("Telekinetic Grab").results().first());
            bot.setFood(ActionBar.newQuery().names(bot.getFoodType()).results().first());
            bot.setChecked(true);
        }
        if (bot.getFood() == null){
            ActionBar.Slot foodSlot = ActionBar.newQuery().names(bot.getFoodType()).results().first();
            if(foodSlot==null){
                if(InterfaceWindows.getInventory().isOpen() || InterfaceWindows.getInventory().open()){
                    if(Mouse.drag(Inventory.getItems(bot.getFoodType()).first(), Interfaces.newQuery().containers(1430).actions("Customise-keybind").results().get(1))){
                        Execution.delayUntil(() -> ActionBar.newQuery().names(bot.getFoodType()).results().first() != null, 500, 1000);
                        bot.setFood(ActionBar.newQuery().names(bot.getFoodType()).results().first());
                    }else if(Mouse.isPressed()){
                        Mouse.release(LEFT);
                    }
                }
            }else{
                bot.setFood(foodSlot);
            }
        }
        if(bot.getTelegrab() == null || !bot.getFood().getName().equals("Telekinetic Grab")) {
            ActionBar.Slot telegrabSlot = ActionBar.newQuery().names("Telekinetic Grab").results().first();
            if (telegrabSlot == null){
                if(InterfaceWindows.getMagic().isOpen() || InterfaceWindows.getMagic().open()) {
                    if (InterfaceWindows.getMagic().isOpen() || InterfaceWindows.getMagic().open()) {
                        InterfaceContainer inter = InterfaceContainers.getAt(1461);
                        if (inter != null) {
                            InterfaceComponent childItem = inter.getComponents().get(1).getChild(32);
                            if (childItem != null && childItem.isVisible()) {
                                if(Mouse.drag(childItem, InterfaceContainers.getAt(1430).getComponent(62))) {
                                    Execution.delayUntil(() -> ActionBar.newQuery().names("Telekinetic Grab").results().first() != null, 500, 1000);
                                    bot.setTelegrab(ActionBar.newQuery().names("Telekinetic Grab").results().first());
                                }else if(Mouse.isPressed()){
                                    Mouse.release(LEFT);
                                }
                            } else {
                                InterfaceComponent skillButton = Interfaces.newQuery().actions("Skilling").visible().results().first();
                                if (skillButton != null) {
                                    skillButton.click();
                                }
                            }
                        }
                    }
                }
            }else{
                bot.setTelegrab(telegrabSlot);
            }
        }
    }
}
