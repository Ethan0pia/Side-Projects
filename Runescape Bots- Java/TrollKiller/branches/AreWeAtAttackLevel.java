package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeAtAttackLevel extends BranchTask {

    private AreWeAtRangedLevel areWeAtRangedLevel;
    private AreWeInNorthRoom areWeInNorthRoom;
    private TrollKiller bot;

    public AreWeAtAttackLevel(TrollKiller bot){
        this.bot=bot;
        areWeInNorthRoom = new AreWeInNorthRoom(bot);
        areWeAtRangedLevel = new AreWeAtRangedLevel(bot);
    }


    @Override
    public boolean validate() {
        return Skill.ATTACK.getBaseLevel()>=bot.getAttackTarget();
    }

    @Override
    public TreeTask failureTask() {
        return areWeInNorthRoom;
    }

    @Override
    public TreeTask successTask() {
        return areWeAtRangedLevel;
    }
}
