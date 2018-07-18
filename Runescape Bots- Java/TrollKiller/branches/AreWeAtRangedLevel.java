package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeAtRangedLevel extends BranchTask {

    private AreWeAtMagicLevel areWeAtMagicLevel;
    private AreWeInEastRoom areWeInEastRoom;
    private TrollKiller bot;

    public AreWeAtRangedLevel(TrollKiller bot){
        this.bot=bot;
        areWeInEastRoom = new AreWeInEastRoom(bot);
        areWeAtMagicLevel = new AreWeAtMagicLevel(bot);
    }


    @Override
    public boolean validate() {
        return Skill.RANGED.getBaseLevel()>=bot.getRangedTarget();
    }

    @Override
    public TreeTask failureTask() {
        return areWeInEastRoom;
    }

    @Override
    public TreeTask successTask() {
        return areWeAtMagicLevel;
    }
}
