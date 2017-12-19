package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.osrs.local.hud.interfaces.MakeAllInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class CastSpellOnObelisk extends LeafTask {

    private OsrsOrbMaker bot;
    private Area obeliskArea = new Area.Circular(new Coordinate(3088,3570,0),6);

    public CastSpellOnObelisk(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("CastSpellOnObelisk");
        if(MakeAllInterface.isOpen()) {
            if(Keyboard.type(" ",false)){
                Execution.delayUntil(() -> !Inventory.contains(567) || Health.getCurrent()<8, 90000);
            }
        }else if(Magic.CHARGE_AIR_ORB.activate()){
            GameObject obelisk = GameObjects.newQuery().names("Obelisk of Air").within(obeliskArea).results().nearest();
            if(obelisk!=null){
                Execution.delay(100,200);
                if(obelisk.interact("Cast")) {
                    Execution.delayUntil(()->!bot.getPlayer().isMoving() || MakeAllInterface.isOpen(),3000);
                }
            }
        }
    }
}
