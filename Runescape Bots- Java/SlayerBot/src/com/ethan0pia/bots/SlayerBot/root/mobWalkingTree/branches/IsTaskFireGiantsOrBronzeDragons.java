package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsTaskFireGiantsOrBronzeDragons extends BranchTask {

    private AmIByDungeonEntranceFireGiants amibydungeonentrancefiregiants;
    private IsTaskSkeletonsOrZombies istaskskeletonsorzombies;
    private OpiaSlayer bot;

    public IsTaskFireGiantsOrBronzeDragons(OpiaSlayer bot){
        this.bot=bot;
        amibydungeonentrancefiregiants = new AmIByDungeonEntranceFireGiants(bot);
        istaskskeletonsorzombies = new IsTaskSkeletonsOrZombies(bot);
    }


    @Override
    public boolean validate() {
        String mob = bot.getMonster().getMobName();
        return mob.equalsIgnoreCase("Fire giant") || mob.equalsIgnoreCase("Bronze dragon");
    }

    @Override
    public TreeTask failureTask() {
        return istaskskeletonsorzombies;
    }

    @Override
    public TreeTask successTask() {
        return amibydungeonentrancefiregiants;
    }
}
