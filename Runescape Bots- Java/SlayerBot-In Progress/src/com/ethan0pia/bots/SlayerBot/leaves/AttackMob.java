package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.status.CombatGauge;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Attacks second closest monster.
 */
public class AttackMob extends LeafTask {

    private GoodAssSlayerBot Bot;

    public AttackMob(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("fuck1!");
        int task = Varbits.load(7923).getValue();
        String mobName = Bot.mobList.getMobName(task);
        Area mobArea = Bot.mobList.getMobArea(task);

        if (mobName != null && mobArea != null && Bot.player != null) {
            Npc mob = Npcs.getLoadedWithin(mobArea, mobName).sortByDistance().limit(0, 2).random();
            if (mob != null) {
                if (!mob.isVisible()) {
                    Camera.turnTo(mob);
                }
                if (!Bot.player.isMoving()) {
                    if(!mob.interact("Attack", mobName)){
                        Camera.concurrentlyTurnTo(mob);
                    }
                }
            }
        }
    }
}
