package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.EnterQbdLair;
import com.ethan0pia.bots.QBDBot.leaves.WalkQbdEntrance;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeByQbdEntrance extends BranchTask {

    private EnterQbdLair enterqbdlair;
    private WalkQbdEntrance walkqbdentrance;
    private OpiaQBD bot;
    private Area.Circular entrance = new Area.Circular(new Coordinate(1199,6499,0),5);

    public AreWeByQbdEntrance(OpiaQBD bot){
        this.bot = bot;
        walkqbdentrance = new WalkQbdEntrance(bot);
        enterqbdlair = new EnterQbdLair(bot);
    }

    @Override
    public boolean validate() {
        return entrance.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkqbdentrance;
    }

    @Override
    public TreeTask successTask() {
        return enterqbdlair;
    }
}
