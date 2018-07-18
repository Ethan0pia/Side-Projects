package com.ethan0pia.bots.CockroachKiller.leaves;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EatFood extends LeafTask {

    private Roach bot;

    public EatFood(Roach bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("10");
            SpriteItem food = Inventory.newQuery().actions("Eat").results().first();
            if(food!=null && food.interact("Eat")){
                Execution.delayWhile(food::isValid,200,1000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
