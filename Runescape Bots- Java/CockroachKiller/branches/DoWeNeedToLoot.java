package com.ethan0pia.bots.CockroachKiller.branches;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.CockroachKiller.leaves.AttackRoach;
import com.ethan0pia.bots.CockroachKiller.leaves.LootItems;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class DoWeNeedToLoot extends BranchTask {

    private LootItems lootItems;
    private AttackRoach attackRoach;
    private Roach bot;
    private Area.Rectangular mobArea = new Area.Rectangular(new Coordinate(3145,4273,3),new Coordinate(3160,4281,3));

    public DoWeNeedToLoot(Roach bot){
        this.bot=bot;
        lootItems = new LootItems(bot);
        attackRoach = new AttackRoach(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("7");
        return GroundItems.newQuery().within(mobArea).names("Rune sq shield","Uncut diamond","Uncut ruby","Mithril ore","Adamantite ore","Off-hand rune scimitar","Rune scimitar","Uncut emerald").results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return lootItems;
    }

    @Override
    public TreeTask successTask() {
        return attackRoach;
    }
}
