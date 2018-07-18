package com.ethan0pia.bots.CockroachKiller.branches;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoWeHaveFood extends BranchTask {

    private AreWeAtCockroaches areWeAtCockroaches;
    private AreWeAtBank areWeAtBank;
    private Roach bot;

    public DoWeHaveFood(Roach bot){
        this.bot=bot;
        areWeAtBank = new AreWeAtBank(bot);
        areWeAtCockroaches = new AreWeAtCockroaches(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("5");
        return Inventory.newQuery().actions("Eat").results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return areWeAtCockroaches;
    }

    @Override
    public TreeTask successTask() {
        return areWeAtBank;
    }
}
