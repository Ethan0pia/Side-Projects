package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Am I at the monster's location.
 */
public class AtMonster extends BranchTask {

    private GoodAssSlayerBot Bot;

    public AtMonster(GoodAssSlayerBot bot){
        Bot=bot;
        checkground = new CheckGround(Bot);
        incombatnotatmob = new InCombatNotAtMob(Bot);
    }

    private CheckGround checkground;
    private InCombatNotAtMob incombatnotatmob;

    @Override
    public boolean validate() {
        int task = Varbits.load(7923).getValue();
        Area monsterArea = Bot.mobList.getMobArea(task);

        return monsterArea != null && Bot.player!=null && monsterArea.contains(Bot.player);
    }

    @Override
    public TreeTask failureTask() {
        return incombatnotatmob;
    }

    @Override
    public TreeTask successTask() {
        return checkground;
    }
}
