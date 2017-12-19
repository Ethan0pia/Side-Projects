package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves.AttackMob;

/**
 * NOTES: done
 * Checks if I am in combat.
 */
public class AmIInCombat extends BranchTask {

    private IsTaskAStrykeWyrm isTaskAStrykeWyrm;
    private AttackMob attackmob;
    private OpiaSlayer bot;

    public AmIInCombat(OpiaSlayer bot){
        this.bot=bot;
        attackmob = new AttackMob(bot);
        isTaskAStrykeWyrm = new IsTaskAStrykeWyrm(bot);
    }

    @Override
    public boolean validate() {
        return bot.getPlayer().getAnimationId()==-1;
    }

    @Override
    public TreeTask failureTask() {
        return isTaskAStrykeWyrm;
    }

    @Override
    public TreeTask successTask() {
        return attackmob;
    }
}
