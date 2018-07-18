package com.ethan0pia.bots.QBDBot.EatingThread.branches;

import com.ethan0pia.bots.QBDBot.EatingThread.leaves.DrinkPrayerPotion;
import com.ethan0pia.bots.QBDBot.EatingThread.leaves.QuickTeleport;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class DoWeHavePrayerPotion extends BranchTask {

    private DrinkPrayerPotion drinkprayerpotion = new DrinkPrayerPotion();
    private QuickTeleport quickteleport = new QuickTeleport();

    @Override
    public boolean validate() {
        Environment.getLogger().debug("EatingThread:DoWeHavePrayerPotion");
        SpriteItem potion = Inventory.newQuery().ids(139, 141,143,2434,23243,23245,23247,23249,23251,23253,3024,3026,3028,3030,23399,23401,23403,23405,23407,23409).results().first();
        return potion!=null;
    }

    @Override
    public TreeTask failureTask() {
        return quickteleport;
    }

    @Override
    public TreeTask successTask() {
        return drinkprayerpotion;
    }
}
