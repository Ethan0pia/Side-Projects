package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.PrayerAltar;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class AreWeByAltar extends BranchTask {

    private PrayerAltar prayeraltar;
    private AreWeInCombat areweincombat;
    private OpiaQBD bot;
    private Area.Circular altar = new Area.Circular(new Coordinate(2906,3501,0),5);

    public AreWeByAltar(OpiaQBD bot){
        this.bot = bot;
        prayeraltar = new PrayerAltar(bot);
        areweincombat = new AreWeInCombat(bot);
    }

    @Override
    public boolean validate() {
        return altar.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return areweincombat;
    }

    @Override
    public TreeTask successTask() {
        return prayeraltar;
    }
}
