package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkMob extends LeafTask {

    private OpiaSpiritualMages bot;
    private Area mobArea = new Area.Circular(new Coordinate(2887,5358,0), 12);

    public WalkMob(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(Bank.isOpen()&& Bank.close()){
            Execution.delayUntil(()->!Bank.isOpen(),2000);
        }
        bot.getUtils().walkPath(mobArea.getCenter());
        bot.getUtils().stuckCheck(33);
    }
}
