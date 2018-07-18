package com.ethan0pia.bots.DeathHandler;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.CoordinatePath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.region.Region;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.MoneyPouch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.AbstractBot;
import com.runemate.game.api.script.framework.tree.LeafTask;
//import com.runemate.game.events.GameEventHandler;

import java.util.Collections;
import java.util.List;

public class RS3GrimReaper extends LeafTask {//GameEventHandler {

    private GameObject death;
    private boolean outOfCoins = false;
    private int count = 0;
    private static int previous = 0;

    public RS3GrimReaper() {
       // super(PRIORITY_NORMAL);
    }

    private static GameObject getDeathEntity() {
        return GameObjects.newQuery().names("Death", "Frank").results().first();
    }

    private static InterfaceComponent getExitLocationButton() {
        return Interfaces.newQuery().visible().containers(1578).types(InterfaceComponent.Type.LABEL).textContains("Exit via the portal").results().first();
    }

    private static InterfaceComponent getPopupConfirmButton() {
        return Interfaces.newQuery().visible().containers(1626).types(InterfaceComponent.Type.LABEL).texts("Yes").results().first();
    }

    private static InterfaceComponent getCollectionConfirmButton() {
        return Interfaces.newQuery().visible().containers(1626).types(InterfaceComponent.Type.CONTAINER).actions("Confirm").results().first();
    }

    private static boolean hasCollectedItems() {
        //This varbit represents number of 6 second intervals you've been dead...
        Varbit vb = Varbits.load(28112);
        /*
        ******Clicking exit disables the autowalk to death that runescape does.
        ******This should not impact other times the handler is executed, unless it were stopped
         ******right as they were leaving death. In that case, they will have to wait 7 seconds to exit.
        */
        if(vb!=null) {
            int value = vb.getValue();
            if (previous != value && value != 0) {
                previous = value;
            }
            if (value == 0 && previous == 0) {
                Execution.delayUntil(() -> {
                    Varbit secondInterval = Varbits.load(28112);
                    return secondInterval != null && secondInterval.getValue() != 0;
                }, 500, 7000);
            }
        }
        return vb != null && vb.getValue()==0 && (!Inventory.isEmpty() || !Equipment.isEmpty());
    }

    private static boolean isOutOfCoins(InterfaceComponent costComponent) {
        return (Inventory.getQuantity("Coins") + MoneyPouch.getContents()) < Integer.parseInt(costComponent.getText().replace("Total cost to save:", "").replace(System.lineSeparator(), "").replace(",", ""));
    }

    //Override
    public GameEvents.GameEvent getAPIEventInstance() {
        return GameEvents.RS3.GRIM_REAPERS_OFFICE;
    }

    //@Override
    public boolean isValid() {
        return Region.isUnique() && (death = getDeathEntity()) != null;
    }

    @Override
    public void execute() {
        if(isValid()) {
            run();
        }
    }

    //@Override
    public void run() {
        Player player = Players.getLocal();
        if(player!=null) {
            ChatDialog.Continue basic = ChatDialog.getContinue();
            if (basic != null) {
                basic.select();
            } else {
                ChatDialog.Option option = ChatDialog.getOption("Hello again.", "Yes",
                        "Pleased to meet you.", "Great! How do I get back?", "Goodbye!");
                if (option != null) {
                    option.select();
                } else {
                    InterfaceComponent exitLocation = getExitLocationButton();
                    if (exitLocation != null) {
                        if (exitLocation.interact("Select")) {
                            Execution.delayWhile(this::isValid, 3000, 5000);
                        }
                    } else {
                        InterfaceComponent confirmationPopup = getPopupConfirmButton();
                        if (confirmationPopup != null) {
                            if (confirmationPopup.interact("Select") || confirmationPopup.interact("Continue")) {
                                Execution.delayWhile(this::isValid, 3000, 4000);
                            }
                        } else {
                            InterfaceComponent collectionButton = getCollectionConfirmButton();
                            GameObject exit = GameObjects.newQuery().appearance(103802).results().first();
                            if (collectionButton != null) {
                                InterfaceComponent costComp = Interfaces.newQuery().containers(1626).types(InterfaceComponent.Type.LABEL).textContains("Total cost to save:").results().first();
                                if (costComp != null && isOutOfCoins(costComp)) {
                                    if (count < 5) {
                                        count++;
                                    } else {
                                        AbstractBot bot = Environment.getBot();
                                        if (bot != null) {
                                            System.err.println("[WARN: Death Handler] Not enough coins to retrieve all items, stopping]");
                                            bot.stop("You died and don't have enough coins to recover all of your items from the Grim Reaper");
                                        }
//                                  outOfCoins = true;
                                    }
                                } else if (collectionButton.interact("Confirm")) {
                                    count = 0;
                                    Execution.delayWhile(collectionButton::isValid, 2000, 3000);
                                }
                            } else if (hasCollectedItems()) {
                                if (exit != null) {
                                    if (exit.isVisible()) {
                                        if (exit.interact("Exit")) {
                                            Execution.delayUntil(() -> getExitLocationButton() != null || !isValid(), 2000, 3000);
                                        }
                                    } else {
                                        CoordinatePath path = RegionPath.buildTo(exit);
                                        if (path == null) {
                                            path = BresenhamPath.buildTo(exit);
                                        }
                                        if (path != null) {
                                            //*****added camera turn to fix issue where it tries to click through interfaces if they are in the way.
                                            ViewportPath viewPath = ViewportPath.convert(path);
                                            if (!viewPath.step()) {
                                                Camera.concurrentlyTurnTo(viewPath.getNext(), Random.nextDouble(0.55, 0.666));
                                            }
                                        }
                                    }
                                }
                            } else if (death.isVisible()) {
                                if (death.interact("Reclaim items") || death.interact("Talk-to")) {
                                    Execution.delayUntil(() -> ChatDialog.getContinue() != null
                                            || getCollectionConfirmButton() != null, 2000, 3000);
                                }
                                //*****Cover a possible walking issue that should never be needed, but is backup
                                //*****trying to cover all the possible edge cases
                                else if(Distance.to(death)>6){
                                    Coordinate manualSpot = death.getPosition().derive(0,-4);
                                    if (manualSpot.isVisible() && manualSpot.interact("Walk here")) {
                                        Execution.delayUntil(player::isMoving, 500, 1200);
                                        Execution.delayWhile(player::isMoving);
                                    } else {
                                        Camera.concurrentlyTurnTo(manualSpot, Random.nextDouble(0.5,0.85));
                                    }
                                }
                            } else if(new Area.Circular(exit.getPosition(),8).contains(player)){
                                //*****wait for auto walk to engage if close to exit
                                if(new Area.Circular(exit.getPosition(),2).contains(player)) {
                                    Execution.delayUntil(player::isMoving, 1000, 3000);
                                }
                                //*****check if auto walk engaged
                                //*****should never execute this first if, but it is backup for those rare problem times.
                                //*****trying to cover all the possible edge cases
                                if(!player.isMoving()) {
                                    Coordinate manualSpot = player.getPosition().derive(0, 6);
                                    if (manualSpot != null) {
                                        if (manualSpot.isVisible() && manualSpot.interact("Walk here")) {
                                            Execution.delayUntil(player::isMoving, 500, 1200);
                                            Execution.delayWhile(player::isMoving);
                                        } else {
                                            Camera.concurrentlyTurnTo(manualSpot, Random.nextDouble(0.5,0.85));
                                        }
                                    }
                                }else{
                                    Execution.delayWhile(player::isMoving);
                                }
                            }else{
                                Camera.concurrentlyTurnTo(death);
                            }
                        }
                    }
                }
            }
        }
    }

    //@Override
    public List<GameEvents.GameEvent> getChildren(AbstractBot bot) {
        return Collections.emptyList();
    }

//    public boolean isOutOfCoins() {
//        return outOfCoins;
//    }
}