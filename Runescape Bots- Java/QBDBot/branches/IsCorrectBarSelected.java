package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.SelectCombatBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCorrectBarSelected extends BranchTask {

    private AreWeGeared arewegeared;
    private SelectCombatBar selectcombatbar;
    private OpiaQBD bot;

    public IsCorrectBarSelected(OpiaQBD bot){
        this.bot = bot;
        selectcombatbar = new SelectCombatBar(bot);
        arewegeared = new AreWeGeared(bot);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return selectcombatbar;
    }

    @Override
    public TreeTask successTask() {
        return arewegeared;
    }
}
