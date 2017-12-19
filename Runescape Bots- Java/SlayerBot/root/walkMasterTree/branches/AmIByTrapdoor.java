package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * 
 */
public class AmIByTrapdoor extends BranchTask {

    private IsTrapdoorOpen istrapdooropen;
    private AmIInsideDungeon amiinsidedungeon;
    private OpiaSlayer bot;
    private Area trapdoorArea = new Area.Circular(new Coordinate(3094,3471,0),6);


    public AmIByTrapdoor(OpiaSlayer bot){
        this.bot=bot;
        amiinsidedungeon = new AmIInsideDungeon(bot);
        istrapdooropen = new IsTrapdoorOpen(bot);
    }


    @Override
    public boolean validate() {
        return trapdoorArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amiinsidedungeon;
    }

    @Override
    public TreeTask successTask() {
        return istrapdooropen;
    }
}
