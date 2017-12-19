package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class AttackGargoyle extends LeafTask {

    private GargSlayer bot;

    public AttackGargoyle(GargSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {

        if (bot.getPlayer() != null && bot.getPlayer().getTarget()==null) {
            Npc mob;
            Npc attacking = Npcs.newQuery().targeting(bot.getPlayer()).actions("Attack").within(new Area.Circular(bot.getPlayer().getPosition(),10)).filter(i-> (i.getSpotAnimationIds().isEmpty() || !(4180 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4190))).results().nearest();
            if(attacking!=null && bot.getPlayer().getTarget()==null){
                mob=attacking;

            }else{
                mob = Npcs.newQuery().actions("Attack").names("Gargoyle").within(new Area.Circular(bot.getPlayer().getPosition(),10)).filter(i-> (i.getSpotAnimationIds().isEmpty() || !(4180 < i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4190) && i.getAnimationId()==-1)).results().sortByDistance().limit(0, 2).random();//4187 is death spot animation
            }
            if (mob != null) {
                if (!mob.isVisible()) {
                    Camera.turnTo(mob);
                }
                if (!bot.getPlayer().isMoving()) {
                    if(!mob.interact("Attack")){
                        Camera.concurrentlyTurnTo(mob);
                        Execution.delay(500, 1000);
                    }else {
                        Execution.delay(500, 1000);
                    }
                }
            }
        }
    }
}
