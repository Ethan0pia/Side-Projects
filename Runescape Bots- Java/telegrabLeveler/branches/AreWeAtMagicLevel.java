package com.ethan0pia.bots.telegrabLeveler.branches;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.telegrabLeveler.leaves.ActivateLodestoneOrStop;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeAtMagicLevel extends BranchTask {

    private ActivateLodestoneOrStop activateLodestoneOrStop;
    private  AreWeInsideCave areweinsidecave;
    private TelegrabLeveler bot;

    public AreWeAtMagicLevel(TelegrabLeveler bot){
        this.bot=bot;
        areweinsidecave = new AreWeInsideCave(bot);
        activateLodestoneOrStop = new ActivateLodestoneOrStop(bot);
    }


    @Override
    public boolean validate() {
        return Skill.MAGIC.getBaseLevel()>=33;
    }

    @Override
    public TreeTask failureTask() {
        return areweinsidecave;
    }

    @Override
    public TreeTask successTask() {
        return activateLodestoneOrStop;
    }
}
