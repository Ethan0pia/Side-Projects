package com.ethan0pia.bots.telegrabLeveler.leaves;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class TalkToShopOwner extends LeafTask {

    private TelegrabLeveler bot;

    public TalkToShopOwner(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Npc shopOwner = Npcs.newQuery().names("Gnome Shopkeeper").results().nearest();
            if (shopOwner != null) {
                shopOwner.interact("Trade");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
