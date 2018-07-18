package com.ethan0pia.bots.QBDBot.EatingThread.branches;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class IsHealthLow extends BranchTask {

    private DoWeHaveFood dowehavefood = new DoWeHaveFood();
    private IsPrayerGood isprayergood = new IsPrayerGood();
    private int whenToEat = 50;

    @Override
    public boolean validate() {
        Environment.getLogger().debug("EatingThread:IsHealthLow");
        if(Health.getCurrentPercent()<whenToEat){
            whenToEat = Random.nextInt(50,80);
            return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isprayergood;
    }

    @Override
    public TreeTask successTask() {
        return dowehavefood;
    }
}
