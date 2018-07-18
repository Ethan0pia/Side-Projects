package com.ethan0pia.bots.QBDBot.leaves;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EnterQbdLair extends LeafTask {
    private OpiaQBD bot;

    public EnterQbdLair(OpiaQBD bot){
        this.bot = bot;
    }

    @Override
    public void execute() {
        GameObject entrance = GameObjects.newQuery().names("Summoning portal").results().nearest();
        if(entrance!=null){
            entrance.interact("Pass through");
        }
    }
}
