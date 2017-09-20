package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EquipStaffandOpenDoor extends LeafTask {

    private Area zanaris =  new Area.Circular(new Coordinate(2437,4442,0),250);
    private OpiaSlayer bot;

    public EquipStaffandOpenDoor(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(!Equipment.contains(772)){
            SpriteItem staff = Inventory.newQuery().ids(772).results().first();
            if (staff != null) {
                Inventory.equip(staff);
                Execution.delayUntil(()->Equipment.contains(772),2000,3000);
            }
        }
        if(Equipment.contains(772)) {
            GameObject door = GameObjects.newQuery().names("Door").actions("Open").results().nearest();
            if(door!=null){
                door.interact("Open");
                Execution.delayUntil(()->zanaris.contains(bot.getPlayer()),4000,6000);
            }
        }
        bot.getUtils().stuckCheck("aa");
    }
}
