package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.ethan0pia.bots.SpiritualMages.root.leaves.BreakHandler;
import com.ethan0pia.bots.SpiritualMages.root.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.concurrent.TimeUnit;


/**
 * NOTES: done
 * Checks to see if we are at the monster
 */
public class ShouldWeBreak extends BranchTask {

    private BreakHandler breakHandler;
    private IsHealthLow checkhealth;
    private EmptyLeaf emptyLeaf;
    private OpiaSpiritualMages bot;
    private boolean breaking = false, afterBreak = false;
    private int breakCount = 0;

    public ShouldWeBreak(OpiaSpiritualMages bot){
        this.bot=bot;
        breakHandler = new BreakHandler(bot);
        checkhealth = new IsHealthLow(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        int breakStart = bot.getInfoUI().getBreakStart(breakCount);
        int duration = bot.getInfoUI().getBreakDuration(breakCount);
        breakHandler.setDuration(duration);
        int breakEnd = breakStart + duration;
        long time = bot.getStopWatch().getRuntime(TimeUnit.MINUTES);

        return (breakStart!=0 && breakEnd!=0 && time >= breakStart && time < breakEnd) || bot.isStopBot();
    }

    @Override
    public TreeTask failureTask() {
        if(breaking){
            breakCount++;
            GameEvents.Universal.LOBBY_HANDLER.enable();
            GameEvents.Universal.LOGIN_HANDLER.enable();
            breaking = false;
            afterBreak = true;
            return emptyLeaf;
        }else if(afterBreak || Skill.SLAYER.getExperience()<100000){
            Execution.delay(5000,8000);
            Camera.concurrentlyTurnTo(Random.nextInt(0,360), Random.nextDouble(0.6,0.9));
            afterBreak = false;
        }

        return checkhealth;
    }

    @Override
    public TreeTask successTask() {
        breaking = true;
        return breakHandler;
    }
}
