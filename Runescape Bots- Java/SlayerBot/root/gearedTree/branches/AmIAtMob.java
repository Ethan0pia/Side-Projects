package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches.DoesMobRequireBasicSpecial;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * Checks to see if we are at the monster
 */
public class AmIAtMob extends BranchTask {

    private ShouldWeAlch shouldWeAlch;
    private DoesMobRequireBasicSpecial walkmob;
    private OpiaSlayer bot;

    public AmIAtMob(OpiaSlayer bot){
        this.bot=bot;
        shouldWeAlch = new ShouldWeAlch(bot);
        walkmob = new DoesMobRequireBasicSpecial(bot);
    }

    @Override
    public boolean validate() {
        Area monsterArea = bot.getMonster().getMobArea();
        return monsterArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkmob;
    }

    @Override
    public TreeTask successTask() {
        return shouldWeAlch;
    }
}
