package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPhaseFour extends BranchTask {

    private AreWeInLootRoom areweinlootroom;
    private IsCorrectBarSelected iscorrectbarselected;
    private OpiaQBD bot;

    public IsPhaseFour(OpiaQBD bot){
        this.bot = bot;
        iscorrectbarselected = new IsCorrectBarSelected(bot);
        areweinlootroom = new AreWeInLootRoom(bot);
    }

    @Override
    public boolean validate() {
        return bot.getStage()==4;
    }

    @Override
    public TreeTask failureTask() {
        return iscorrectbarselected;
    }

    @Override
    public TreeTask successTask() {
        return areweinlootroom;
    }
}
