package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
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
        Environment.getLogger().debug("WalkMob");
        bot.setCurrentTask("Walking to Monsters");
        try {
            if (Bank.isOpen() && Bank.close()) {
                Execution.delayUntil(() -> !Bank.isOpen(), 500,2000);
            }
            bot.getUtils().walkPath(mobArea.getCenter());
            bot.getUtils().stuckCheck(15);
        }catch(Exception e) {
            Environment.getLogger().debug("Failed to walk to mob");
        }
    }
}
