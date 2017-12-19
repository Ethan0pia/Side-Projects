package com.ethan0pia.bots.wellBot.leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class UseWell extends LeafTask {

    @Override
    public void execute() {
        if(MakeXInterface.isOpen()){
            MakeXInterface.close();
        }
        GameObject bank = GameObjects.newQuery().names("Bank chest").within(new Area.Circular(Players.getLocal().getPosition(),8)).results().nearest();
        GameObject well = GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable well").actions("Mix Potions").results().nearest();
        //GameObject forge = GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable forge").actions("Smith").results().nearest();
        //if(well!=null) {
        if(well!=null) {
            //if (well.interact("Mix Potions")) {
            if (well.interact("Take Vials")) {
                Execution.delayUntil(()-> Interfaces.newQuery().textContains("Enter amount:").results().first()!=null, 1200);
                if(Keyboard.type("33",true)){
                    if(bank!=null){
                        bank.hover();
                    }
                    Execution.delayUntil(Inventory::isFull,600);
                }
            }
        }
    }
}