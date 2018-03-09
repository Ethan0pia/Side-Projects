package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.rs3.local.hud.interfaces.MoneyPouch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class tanHides extends LeafTask {

    private Player player;
    private Npc Jack;

	@Override
	public void execute() {
        Jack = Npcs.newQuery().names("Jack Oval").results().nearest();
        player = Players.getLocal();
        if(Jack!=null && player!=null && !player.isMoving()) {
            Camera.concurrentlyTurnTo(Jack);
            Execution.delay(2000, 3000);
            Jack.interact("Tan hide", "Jack Oval");
            Execution.delay(1000,2000);
            if(MakeXInterface.isOpen() && MoneyPouch.getContents()>100){
                if(!MakeXInterface.isSelectedItem("Hard leather")) {
                    if(MakeXInterface.selectItem("Hard leather")) {
                        Execution.delayUntil(()->MakeXInterface.isSelectedItem("Hard leather"));
                    }
                }
                MakeXInterface.confirm();
            }else if (MakeXInterface.isOpen()){
                if(!MakeXInterface.isSelectedItem("Leather")) {
                    if(MakeXInterface.selectItem("Leather")) {
                        Execution.delayUntil(()->MakeXInterface.isSelectedItem("Leather"));
                    }
                }
                MakeXInterface.confirm();
            }
        }
        Execution.delayUntil(() -> !Inventory.contains("Cowhide"), 3000);
    }
}