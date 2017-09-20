package com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
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

            Npc attacking = Npcs.newQuery().targeting(bot.getPlayer()).actions("Attack").within(new Area.Circular(bot.getPlayer().getPosition(),10)).within(bot.getMonster().getMobArea()).filter(i-> (i.getSpotAnimationIds().isEmpty() || !(4180 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4190))).results().nearest();
            if(attacking!=null && bot.getPlayer().getTarget()==null){
                mob=attacking;

            }else if(bot.getMonster().getMobName().equalsIgnoreCase("Werewolf")) {
                mob = Npcs.newQuery().names(bot.getMonster().getMobName()).actions("Attack").within(bot.getMonster().getMobArea()).filter(i-> (i.getSpotAnimationIds().isEmpty() || !(4180 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4190) && i.getAnimationId()==-1)).results().sortByDistance().limit(0, 2).random();

            }else if(bot.getMonster().getMobName().equalsIgnoreCase("Mutated zygomite")){
                mutatedZygomites();

            } else{
                mob = Npcs.newQuery().actions("Attack").names(bot.getMonster().getMobName()).within(bot.getMonster().getMobArea()).filter(i-> (i.getSpotAnimationIds().isEmpty() || !(4180 < i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4190) && i.getAnimationId()==-1)).results().sortByDistance().limit(0, 2).random();//4187 is death spot animation
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
        bot.getUtils().stuckCheck("o");
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
                    Camera.concurrentlyTurnTo(mob);
                }
            }
        }else if(results!=null && Integer.parseInt(results.getText())<200){
            mob.interact(bot.getMonster().getFinishingBlowName());
        }
    }
}
