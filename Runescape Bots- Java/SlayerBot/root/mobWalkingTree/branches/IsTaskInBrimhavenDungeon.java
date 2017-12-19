package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsTaskInBrimhavenDungeon extends BranchTask {

    private AmIByDungeonEntranceFireGiants amibydungeonentrancefiregiants;
    private IsTaskSkeletonsOrZombies istaskskeletonsorzombies;
    private OpiaSlayer bot;

    public IsTaskInBrimhavenDungeon(OpiaSlayer bot){
        this.bot=bot;
        amibydungeonentrancefiregiants = new AmIByDungeonEntranceFireGiants(bot);
        istaskskeletonsorzombies = new IsTaskSkeletonsOrZombies(bot);
    }


    @Override
    public boolean validate() {
        int mob = bot.getMonster().getIndex();
        return mob==16 || mob==58 || mob==26 || mob==59 || mob==29;
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
