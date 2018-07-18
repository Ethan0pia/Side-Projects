package com.ethan0pia.bots.QBDBot.EatingThread.branches;

import com.ethan0pia.bots.QBDBot.EatingThread.leaves.EatFood;
import com.ethan0pia.bots.QBDBot.EatingThread.leaves.QuickTeleport;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class DoWeHaveFood extends BranchTask {

    private EatFood eatfood = new EatFood();
    private QuickTeleport quickteleport = new QuickTeleport();

    @Override
    public boolean validate() {
        Environment.getLogger().debug("EatingThread:DoWeHaveFood");
        return !Inventory.newQuery().actions("Eat").results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return quickteleport;
    }

    @Override
    public TreeTask successTask() {
        return eatfood;
    }
}
