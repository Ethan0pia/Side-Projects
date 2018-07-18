package com.ethan0pia.bots.CockroachKiller.branches;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.CockroachKiller.leaves.EatFood;
import com.ethan0pia.bots.CockroachKiller.leaves.EnterCrevice;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class DoWeNeedToEat extends BranchTask {

    private DoWeNeedToLoot doWeNeedToLoot;
    private EatFood eatFood;
    private Roach bot;
    private int whenToEat = 40;

    public DoWeNeedToEat(Roach bot){
        this.bot=bot;
        eatFood = new EatFood(bot);
        doWeNeedToLoot = new DoWeNeedToLoot(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("6");
        if(Health.getCurrentPercent()<whenToEat){
            whenToEat = Random.nextInt(30,60);
            return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return doWeNeedToLoot;
    }

    @Override
    public TreeTask successTask() {
        return eatFood;
    }
}
