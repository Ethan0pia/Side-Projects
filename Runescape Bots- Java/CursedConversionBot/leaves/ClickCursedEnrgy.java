package com.ethan0pia.bots.CursedConversionBot.leaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ClickCursedEnrgy extends LeafTask {

    @Override
    public void execute() {
        SpriteItem energy = Inventory.newQuery().names("Cursed energy").results().first();
        if(energy !=null && energy.click()){
            Execution.delayUntil(MakeXInterface::isOpen,2000);
        }
    }
}
