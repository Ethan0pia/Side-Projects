package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Am I at the monster's location.
 */
public class AtMonster extends BranchTask {

    private GoodAssSlayerBot Bot;

    public AtMonster(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private CheckGround checkground = new CheckGround(Bot);
    private InCombatNotAtMob incombatnotatmob = new InCombatNotAtMob(Bot);

    @Override
    public boolean validate() {
        int task = Varbits.load(7923).getValue();
        Player player = Players.getLocal();
        Area monsterArea = Bot.mobList.getMobArea(task);
        if(monsterArea != null && player!=null && monsterArea.contains(player)){
            return true;
        }
        else{
            return false;
        }
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
