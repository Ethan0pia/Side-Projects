package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EatFood extends LeafTask {

    @Override
    public void execute() {
        SpriteItem food = Inventory.newQuery().actions("Eat").results().first();
        if(food!=null){
            food.interact("Eat");
        }
    }
}
