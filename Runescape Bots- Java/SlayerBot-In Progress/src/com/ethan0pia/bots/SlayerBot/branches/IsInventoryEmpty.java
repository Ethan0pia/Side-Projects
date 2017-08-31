package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.ethan0pia.bots.SlayerBot.leaves.EmptyInventory;

public class IsInventoryEmpty extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsInventoryEmpty(GoodAssSlayerBot bot){
        Bot=bot;
        emptyInventory = new EmptyInventory(bot);
        haveRoK = new HaveRingOfKinship(bot);
    }

    private EmptyInventory emptyInventory;
    private HaveRingOfKinship haveRoK;

    private Area bankArea = new Area.Rectangular( new Coordinate(2885,3538,0), new Coordinate(2893, 3534, 0));

    @Override
    public boolean validate() {
        return (Inventory.getEmptySlots() > 14);
    }

    @Override
    public TreeTask failureTask() {
        return emptyInventory;
    }

    @Override
    public TreeTask successTask() {
        return haveRoK;
    }
}
