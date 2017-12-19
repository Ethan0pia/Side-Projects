package com.ethan0pia.bots.telegrabLeveler.branches;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.telegrabLeveler.leaves.ActivateLodestoneOrStop;
import com.ethan0pia.bots.telegrabLeveler.leaves.WalkFaladorLodestone;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeAtFaladorLodestone extends BranchTask {

    private WalkFaladorLodestone walkFaladorLodestone;
    private ActivateLodestoneOrStop activateLodestoneOrStop;
    private TelegrabLeveler bot;
    private Area lodestone = new Area.Circular(new Coordinate(2967,3406,0),4);

    public AreWeAtFaladorLodestone(TelegrabLeveler bot){
        this.bot=bot;
        activateLodestoneOrStop = new ActivateLodestoneOrStop(bot);
        walkFaladorLodestone = new WalkFaladorLodestone(bot);
    }


    @Override
    public boolean validate() {
        return lodestone.contains(bot.getPlayer()) || Lodestone.FALADOR.isActivated();
    }

    @Override
    public TreeTask failureTask() {
        return walkFaladorLodestone;
    }

    @Override
    public TreeTask successTask() {
        return activateLodestoneOrStop;
    }
}
