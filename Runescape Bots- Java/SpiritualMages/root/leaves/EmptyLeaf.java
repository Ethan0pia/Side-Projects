package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.concurrent.TimeUnit;

/**
 * NOTES: done
 * 
 */
public class EmptyLeaf extends LeafTask {

    OpiaSpiritualMages bot;

    public EmptyLeaf(OpiaSpiritualMages bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("EmptyLeaf");
        bot.setCurrentTask("No Task");
        Execution.delayUntil(()-> Health.getCurrentPercent()<bot.getWhenToEat(), 100,2000);
        bot.getUtils().stuckCheck(6);
    }
}