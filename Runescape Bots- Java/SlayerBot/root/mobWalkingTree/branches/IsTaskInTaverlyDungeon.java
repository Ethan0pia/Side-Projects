package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsTaskInTaverlyDungeon extends BranchTask {

    private DoIHaveDustyKey doihavedustykey;
    private IsTaskInGWD isTaskInGWD;
    private OpiaSlayer bot;

    public IsTaskInTaverlyDungeon(OpiaSlayer bot){
        this.bot=bot;
        isTaskInGWD = new IsTaskInGWD(bot);
        doihavedustykey = new DoIHaveDustyKey(bot);
    }


    @Override
    public boolean validate() {
        int mob = bot.getMonster().getIndex();
        return mob==25|| mob==31 || mob==30 || mob==27;
    }

    @Override
    public TreeTask failureTask() {
        return isTaskInGWD;
    }

    @Override
    public TreeTask successTask() {
        return doihavedustykey;
    }
}
