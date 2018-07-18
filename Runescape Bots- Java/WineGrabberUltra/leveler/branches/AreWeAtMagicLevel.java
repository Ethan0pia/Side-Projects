package com.ethan0pia.bots.WineGrabberUltra.leveler.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.ActivateLodestone;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeAtMagicLevel extends BranchTask {

    private ActivateLodestone activateLodestone;
    private AreWeInsideCave areweinsidecave;
    private OpiaWineGrabberUltra bot;

    public AreWeAtMagicLevel(OpiaWineGrabberUltra bot){
        this.bot=bot;
        areweinsidecave = new AreWeInsideCave(bot);
        activateLodestone = new ActivateLodestone(bot);
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
        return activateLodestone;
    }
}
