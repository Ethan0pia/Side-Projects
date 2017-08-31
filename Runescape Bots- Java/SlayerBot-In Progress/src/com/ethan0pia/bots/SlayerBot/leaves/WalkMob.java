package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkMob extends LeafTask {

    private GoodAssSlayerBot Bot;

    public WalkMob(GoodAssSlayerBot bot){
        Bot=bot;
    }

    //change coords to reflect which mob
    private Coordinate mobs;

    @Override
    public void execute() {
        int task = Varbits.load(7923).getValue();
        if(Bot.player !=null) {
            Area mobArea = Bot.mobList.getMobArea(task);

            if (new Area.Circular(mobArea.getCenter(), 25).contains(Bot.player) && RegionPath.buildTo(mobArea.getCenter()) != null) {
                System.out.println("wrong path");
                final RegionPath path = RegionPath.buildTo(mobArea.getCenter());
                if (path != null) {
                    path.step(true);
                }
            } else {
                if (!mobArea.contains(Bot.player)) {
                    Area caveArea;
                    switch (task) {
                        case 111:
                            caveArea = new Area.Circular( new Coordinate(2217, 4532, 0), 250);
                            defaultSpecial("Cave Entrance","Enter",111, caveArea);
                            break;
                        case 39:
                            caveArea = new Area.Circular( new Coordinate(2217, 4532, 0), 250);
                            defaultSpecial("Cave Entrance","Enter",39, caveArea);
                            break;
                        case 11:
                            skeletons();
                            break;
                        case 18:
                            caveArea = new Area.Circular( new Coordinate(2214, 4394,0), 250);
                            defaultSpecial("Cave entrance","Enter",18, caveArea);
                            break;
                        case 10:
                            zombies();
                            break;
                        default:
                            final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(Bot.mobList.getWebCoord(task));
                            if (path != null) {
                                path.step(true);
                            }
                    }
                }
            }
        }
    }

    //for 1 interaction to get to mob
    private void defaultSpecial(String cave, String interaction, int task, Area caveArea) {
        GameObject caveObject = GameObjects.newQuery().names(cave).actions(interaction).within(new Area.Circular(Bot.player.getPosition(), 7)).results().nearest();
        if (caveObject != null) {
            caveObject.interact(interaction);
            Execution.delayUntil(() -> caveArea.contains(Bot.player), 3000, 5000);
        } else if (!caveArea.contains(Bot.player)) {
            final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(Bot.mobList.getWebCoord(task));
            if (path != null) {
                path.step(true);
            }
        } else {
            RegionPath path = RegionPath.buildTo(Bot.mobList.getMobArea(task).getRandomCoordinate());
            if (path != null) {
                path.step(true);
            } else {
                BresenhamPath path2 = BresenhamPath.buildTo(Bot.mobList.getMobArea(task).getRandomCoordinate());
                if (path2 != null) {
                    path2.step(true);
                }
            }
        }
    }

    //for getting into edgeville dungeon
    private void edgevilleDungeon() {
        Area caveArea = new Area.Circular(new Coordinate(3097, 9869, 0), 300);
        GameObject cave = GameObjects.newQuery().names("Trapdoor").within(new Area.Circular( Bot.player.getPosition(), 5)).results().nearest();
        if(!caveArea.contains(Bot.player) && !new Area.Circular(new Coordinate(3096, 3468, 0), 7).contains(Bot.player) && new Area.Circular(new Coordinate(3096, 3468, 0), 32).contains(Bot.player)) {
            BresenhamPath path2 = BresenhamPath.buildTo(new Coordinate(3094, 3471, 0));
            if (path2 != null) {
                path2.step(true);
            }
        }else if (!new Area.Circular(new Coordinate(3096, 3468, 0), 30).contains(Bot.player) && !caveArea.contains(Bot.player)){
            final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(Bot.mobList.getWebCoord(11));
            if (path != null) {
                path.step(true);
            }
        }
        if (cave != null && !Bot.player.isMoving() && !caveArea.contains(Bot.player)) {
            if (!cave.isVisible()) {
                Camera.turnTo(cave);
                Execution.delay(1000, 1500);
            }
            if (!cave.interact("Climb-down")) {
                cave.interact("Open");
            }
            Execution.delayUntil(() -> caveArea.contains(Bot.player), 1500, 2500);
        }
    }


    private void skeletons(){
        if (Bot.player != null) {
            edgevilleDungeon();
            Area caveArea = new Area.Circular( new Coordinate(3097,9871, 0), 250);
            if(caveArea.contains(Bot.player)){
                BresenhamPath path = BresenhamPath.buildTo(Bot.mobList.getMobArea(11).getRandomCoordinate());
                if (path != null) {
                    path.step(true);
                }
            }
        }
    }

    private void zombies(){
        if (Bot.player != null) {
            edgevilleDungeon();
            Area caveArea = new Area.Circular( new Coordinate(3097,9869, 0), 250);
            if(caveArea.contains(Bot.player)) {
                Area pastGate = new Area.Rectangular(new Coordinate(3104, 9950, 0), new Coordinate(3151, 9860, 0));
                if (!pastGate.contains(Bot.player) && GameObjects.newQuery().names("Metal door").within(new Area.Circular(Bot.player.getPosition(), 8)).results().nearest() == null) {
                    BresenhamPath path2 = BresenhamPath.buildTo(new Coordinate(3102, 9909, 0));
                    if (path2 != null) {
                        path2.step(true);
                    }
                } else {
                    GameObject caveObject = GameObjects.newQuery().names("Metal door").actions("Open").within(new Area.Circular(Bot.player.getPosition(), 7)).results().nearest();
                    if (caveObject != null) {
                        if (caveObject.interact("Open")) {
                            Execution.delay(3000, 5000);
                        }
                    } else {
                        RegionPath path = RegionPath.buildTo(Bot.mobList.getMobArea(10).getRandomCoordinate());
                        if (path != null) {
                            path.step(true);
                        } else {
                            BresenhamPath path2 = BresenhamPath.buildTo(Bot.mobList.getMobArea(10).getRandomCoordinate());
                            if (path2 != null) {
                                path2.step(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
