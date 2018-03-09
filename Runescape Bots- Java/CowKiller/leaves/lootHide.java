package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.rs3.local.hud.interfaces.LootInventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.region.Players;

import java.util.List;

public class lootHide extends LeafTask {

    private Player player;
    private GroundItem hide;
    private final Area cows= new Area.Rectangular(new Coordinate(2877,3497,0), new Coordinate(2892,3479,0));
    private int fails = 0;

	@Override
	public void execute() {

	    player = Players.getLocal();
	    if(player != null) {
            if (!player.isMoving()) {
                hide = GroundItems.newQuery().names("Cowhide").within(cows).results().nearest();
                if (hide != null) {
                    if (!LootInventory.isEnabled()) {
                        if (!hide.isVisible()) {
                            Camera.turnTo(hide);
                            Execution.delay(2000, 3000);
                        } else {
                            if (hide.interact("Take")) {
                                fails=0;
                                Execution.delayUntil(() -> !hide.isValid(), 3000);
                            } else {
                                Execution.delay(200,400);
                                if(fails>5) {
                                    Keyboard.typeKey(27);
                                }
                                else if(fails>2){
                                    Camera.concurrentlyTurnTo(hide);
                                }
                                else{
                                    fails++;
                                }
                            }
                        }
                    } else {
                        if(LootInventory.isOpen() && LootInventory.contains("Cowhide")){
                            LootInventory.take("Cowhide");
                            Execution.delay(400, 800);
                        }else if (!LootInventory.isOpen()) {
                            if (!hide.isVisible()) {
                                Camera.turnTo(hide);
                                Execution.delay(2000, 3000);
                            } else if (hide.interact("Take")) {
                                fails=0;
                                Execution.delayUntil(() -> LootInventory.isOpen(), 1000, 3000);
                            } else {
                                Execution.delay(200,400);
                                if(fails>5) {
                                    Keyboard.typeKey(27);
                                }
                                else if(fails>2){
                                    Camera.concurrentlyTurnTo(hide);
                                }
                                else{
                                    fails++;
                                }
                            }
                        }else{
                            LootInventory.close();
                        }
                    }
                }
            }
        }
	}
}