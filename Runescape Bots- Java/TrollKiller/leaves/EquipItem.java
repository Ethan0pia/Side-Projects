package com.ethan0pia.bots.TrollKiller.leaves;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.concurrent.TimeUnit;

/**
 * NOTES:
 * 
 */
public class EquipItem extends LeafTask {

    private TrollKiller bot;
    private int id;

    public EquipItem(TrollKiller bot, int id){
        this.bot=bot;
        this.id=id;
    }


    @Override
    public void execute() {
        try {
            SpriteItem item = Inventory.newQuery().ids(id).results().first();
            if (item != null) {
                if (item.click()) {
                    Execution.delayUntil(() -> !Inventory.contains(id), 200, 3000);
                }
            }else if(bot.getStopWatch().getRuntime(TimeUnit.MINUTES)>1){
                Environment.getLogger().severe("Can't find item to equip.");
                bot.stop("Can't find item to equip");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
