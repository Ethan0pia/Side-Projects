package com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Attack the monster.
 */
public class AttackMob extends LeafTask {

    private OpiaSlayer bot;
    private Npc mob;

    public AttackMob(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {

        if (bot.getMonster().getMobName() != null && bot.getMonster().getMobArea() != null && bot.getPlayer() != null && bot.getPlayer().getTarget()==null) {
            int mobIndex =bot.getMonster().getIndex();
            Npc targetMob = Npcs.newQuery().targeting(bot.getPlayer()).actions("Attack").within(new Area.Circular(bot.getPlayer().getPosition(), 10)).names(bot.getMonster().getMobName()).filter(i -> (i.getSpotAnimationIds().isEmpty() || !(4100 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4200))).results().nearest();
            Npc nonTarget = Npcs.newQuery().targeting(bot.getPlayer()).actions("Attack").within(new Area.Circular(bot.getPlayer().getPosition(), 10)).within(bot.getMonster().getMobArea()).filter(i -> (i.getSpotAnimationIds().isEmpty() || !(4100 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4200))).results().nearest();
            if (targetMob != null && bot.getPlayer().getTarget() == null) {
                mob = targetMob;

            } else if(nonTarget!=null) {
                mob = nonTarget;

            }else if(mobIndex==33) {
                mob = Npcs.newQuery().names(bot.getMonster().getMobName()).actions("Attack").within(bot.getMonster().getMobArea()).filter(i-> (i.getSpotAnimationIds().isEmpty() || !(4180 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4190) && i.getAnimationId()==-1)).results().sortByDistance().limit(0, 2).random();

            }else if(mobIndex==74){
                mutatedZygomites();

            }else if(mobIndex==103 || mobIndex==104){
                if(bot.getPlayer().getTarget()==null) {
                    mob = Npcs.newQuery().names("Mound").actions("Investigate").within(new Area.Circular(new Coordinate(2448, 2902, 0),6)).within(new Area.Circular(new Coordinate(3379,3158,0),6)).results().nearest();
                    if (mob != null) {
                        if (mob.interact("Investigate")) {
                            Execution.delayUntil(() -> Npcs.newQuery().names(bot.getMonster().getMobName()).results().nearest() != null, 4000, 5000);
                        } else {
                            Camera.turnTo(mob);
                        }
                    }
                }
            }else{
                mob = Npcs.newQuery().actions("Attack").names(bot.getMonster().getMobName()).within(bot.getMonster().getMobArea()).filter(i-> (i.getSpotAnimationIds().isEmpty() && i.getAnimationId()==-1) || (!(4180 < i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4190) && i.getAnimationId()==-1 && i.getTarget()==null)).results().sortByDistance().limit(0, 2).random();//4187 is death spot animation
            }
            if (mob != null) {
                if (!mob.isVisible()) {
                    Camera.turnTo(mob);
                }
                if (!bot.getPlayer().isMoving()) {
                    mob.hover();
                    Execution.delay(200,500);
                    if(!mob.interact("Attack")){
                            Camera.turnTo(mob);
                            Execution.delay(1000, 1500);
                    }else {
                        Execution.delay(1000, 2000);
                    }
                }
            }
        }
        bot.getUtils().stuckCheck(15);
    }

    private void mutatedZygomites(){
        mob = Npcs.newQuery().names(bot.getMonster().getMobName()).results().nearest();
        InterfaceComponent results = Interfaces.newQuery().containers(1490).filter(i->i.getId()==97648660).results().first();

        if(mob==null) {

            mob = Npcs.newQuery().actions("Pick").names("Fungi").within(bot.getMonster().getMobArea()).results().sortByDistance().limit(0, 2).random();

            if (mob != null) {
                if (mob.interact("Pick")) {
                    Execution.delayUntil(()->Npcs.newQuery().names(bot.getMonster().getMobName()).results().nearest()!=null,4000, 5000);
                } else {
                    Camera.turnTo(mob);
                }
            }
        }else if(results!=null && Integer.parseInt(results.getText())<200){
            mob.interact(bot.getMonster().getFinishingBlowName());
        }
    }
}
