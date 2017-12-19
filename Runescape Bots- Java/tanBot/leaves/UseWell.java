package com.ethan0pia.bots.tanBot.leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.input.Keyboard;
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

    GameObject crafter = null;

    public GameObject getCrafter() {
        return crafter;
    }

    public void setCrafter(GameObject crafter) {
        this.crafter = crafter;
    }

    @Override
    public void execute() {
        if(crafter!=null) {
            crafter = GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable crafter").actions("Tan Leather").within(new Area.Circular(Players.getLocal().getPosition(),8)).results().nearest();
        }
        if(crafter!=null) {
            //if (well.interact("Mix Potions")) {
            if (crafter.interact("Tan Leather")) {
                Execution.delayUntil(MakeXInterface::isOpen, 600);
                Keyboard.typeKey(" ");
            }
        }
    }
}