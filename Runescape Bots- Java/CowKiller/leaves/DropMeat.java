package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DropMeat extends LeafTask {

    @Override
    public void execute() {
        SpriteItem toDrop = Inventory.newQuery().names("Bones", "Raw beef").results().first();
        if(toDrop!=null){
            if(toDrop.interact("Drop")){
                Execution.delay(250,300);
            }
        }
    }
}
