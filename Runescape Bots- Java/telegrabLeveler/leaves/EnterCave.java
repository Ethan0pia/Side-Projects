package com.ethan0pia.bots.TelegrabLeveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EnterCave extends LeafTask {

    private TelegrabLeveler bot;

    public EnterCave(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            GameObject cave = GameObjects.newQuery().names("Cave entrance").results().nearest();
            if (cave != null) {
                cave.interact("Enter");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
