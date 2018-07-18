package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.SetBankBool;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPhaseOne extends BranchTask {

    private AreWeByQbdEntrance arewebyqbdentrance;
    private SetBankBool setbankbool;
    private OpiaQBD bot;

    public IsPhaseOne(OpiaQBD bot){
        this.bot = bot;
        setbankbool = new SetBankBool(bot);
        arewebyqbdentrance = new AreWeByQbdEntrance(bot);
    }

    @Override
    public boolean validate() {
        return bot.getStage()==1;
    }

    @Override
    public TreeTask failureTask() {
        return setbankbool;
    }

    @Override
    public TreeTask successTask() {
        return arewebyqbdentrance;
    }
}
