package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.WalkBank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeAtBank extends BranchTask {

    private IsBankOpen isbankopen;
    private WalkBank walkbank;
    private OpiaQBD bot;
    private Area.Circular bank = new Area.Circular(new Coordinate(2888,3536,0),5);

    public AreWeAtBank(OpiaQBD bot){
        this.bot = bot;
        walkbank = new WalkBank(bot);
        isbankopen = new IsBankOpen(bot);
    }

    @Override
    public boolean validate() {
        return bank.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkbank;
    }

    @Override
    public TreeTask successTask() {
        return isbankopen;
    }
}
