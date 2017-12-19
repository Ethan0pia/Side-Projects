package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class DoWeHaveTheStuff extends BranchTask {

    private IsBankOpenHaveStuff isbankopenhavestuff;
    private AreWeGettingGlories arewegettingglories;
    private OsrsOrbMaker bot;
    private Area clanWars = new Area.Circular(new Coordinate(3369,3170,0),30);
    private Area portal = new Area.Circular(new Coordinate(3326,4754,0),20);

    public DoWeHaveTheStuff(OsrsOrbMaker bot){
        this.bot=bot;
        isbankopenhavestuff = new IsBankOpenHaveStuff(bot);
        arewegettingglories = new AreWeGettingGlories(bot);
    }

    @Override
    public boolean validate() {
        return Equipment.contains(1381) && Inventory.contains(564) && Inventory.contains(567) &&
                ((!clanWars.contains(bot.getPlayer()) && !portal.contains(bot.getPlayer())) || Equipment.contains(1706) || Equipment.contains(1708) || Equipment.contains(1710) || Equipment.contains(1712) || Equipment.contains(11976) || Equipment.contains(11978)) &&
                (Equipment.contains(2552) || Equipment.contains(2554) || Equipment.contains(2556) || Equipment.contains(2558) || Equipment.contains(2560) || Equipment.contains(2562)|| Equipment.contains(2564)|| Equipment.contains(2566));
    }

    @Override
    public TreeTask failureTask() {
        return arewegettingglories;
    }

    @Override
    public TreeTask successTask() {
        return isbankopenhavestuff;
    }
}
