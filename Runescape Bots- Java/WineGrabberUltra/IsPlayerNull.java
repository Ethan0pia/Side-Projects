package com.ethan0pia.bots.WineGrabberUltra;

import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPlayerNull extends BranchTask {

    private IsShiftOver isShiftOver;
    private EmptyLeaf emptyLeaf;

    private OpiaWineGrabberUltra bot;
    public IsPlayerNull(OpiaWineGrabberUltra bot){
        this.bot=bot;
        isShiftOver = new IsShiftOver(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        Environment.getLogger().debug("IsPlayerNull");
        bot.setPlayer(Players.getLocal());
        return bot.getPlayer()!=null && bot.isGo();
    }

    @Override
    public TreeTask failureTask() {
        return emptyLeaf;
    }

    @Override
    public TreeTask successTask() {
        bot.setCurrentMagicLevel(Skill.MAGIC.getBaseLevel());
        return isShiftOver;
    }
}
