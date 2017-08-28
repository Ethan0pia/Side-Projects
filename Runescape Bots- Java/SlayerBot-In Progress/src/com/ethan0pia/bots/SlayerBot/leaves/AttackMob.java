package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Attacks second closest monster.
 */
public class AttackMob extends LeafTask {

    private GoodAssSlayerBot Bot;

    public AttackMob(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {

        int task = Varbits.load(7923).getValue();
        String mobName = Bot.mobList.getMobName(task);
        Area mobArea = Bot.mobList.getMobArea(task);
        Player player = Players.getLocal();

        if(mobName!=null && mobArea != null){
            if(player.getTarget()==null) {
                Npc mob = Npcs.getLoadedWithin(mobArea, mobName).sortByDistance().random();
                if(mob !=null && player!=null) {
                    if (!mob.isVisible()) {
                        Camera.turnTo(mob);
                    }
                    if (!player.isMoving()) {
                        mob.interact("Attack", mobName);
                    }
                }
            }
        }
    }
}
