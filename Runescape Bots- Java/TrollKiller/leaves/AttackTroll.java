package com.ethan0pia.bots.TrollKiller.leaves;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.WorldOverview;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class AttackTroll extends LeafTask {

    private TrollKiller bot;
    private String name;
    private int hops = 0;

    public AttackTroll(TrollKiller bot, String name){
        this.bot=bot;
        this.name=name;
    }


    @Override
    public void execute() {
        try {
            Npc mob;
            Player player = bot.getPlayer();
            Actor target = player.getTarget();
            if(!hop(player)) {
                if(Camera.getPitch()<0.5){
                    Camera.concurrentlyTurnTo(Random.nextDouble(0.6,0.9));
                }
                if (player.getAnimationId() == -1 &&
                        (target == null ||
                                (!target.getSpotAnimationIds().isEmpty() &&
                                        (3500 > target.getSpotAnimationIds().get(0) ||
                                                target.getSpotAnimationIds().get(0) > 4500)))) {
                    Npc targetMob = Npcs.newQuery().targeting(player).actions("Attack").names(name).filter(i -> i.getTarget() != null && (i.getSpotAnimationIds().isEmpty() || !(3500 < i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4500))).results().nearest();
                    if (targetMob != null) {
                        mob = targetMob;
                    } else {
                        mob = Npcs.newQuery().actions("Attack").names(name).filter(i -> (i.getSpotAnimationIds().isEmpty() && i.getAnimationId() == -1) || (!(3500 < i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4500) && i.getAnimationId() == -1 && i.getTarget() == null)).results().nearest();
                    }
                    if (mob != null) {
                        if (mob.isVisible()) {
                            if (mob.interact("Attack") || mob.click()) {
                                Execution.delayWhile(() -> mob.isValid() && (mob.getSpotAnimationIds().isEmpty() || !(3500 < mob.getSpotAnimationIds().get(0) && mob.getSpotAnimationIds().get(0) < 4500)), 2000);
                            }
                        } else {
                            Camera.concurrentlyTurnTo(mob, Random.nextDouble(0.6,0.9));
                            Execution.delayUntil(mob::isVisible, 100, 1000);
                            if(!mob.isVisible()){
                                BresenhamPath path = BresenhamPath.buildTo(mob);
                                if(path!=null){
                                    path.step();
                                }
                            }
                        }
                    }
                } else {
                    Execution.delayUntil(() -> player.getAnimationId() == -1, 2000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean hop(Player player) {
        if (5 > hops) {
            Player others = Players.newQuery().within(new Area.Circular(player.getPosition(),10)).filter(i -> i!=null && !i.getName().equals(player.getName()) && i.getStanceId()<18000).results().first();
            if (others != null) {
                int world = Worlds.getCurrent();
                int attack = Skill.ATTACK.getBaseLevel();
                Execution.delayUntil(()->player.getTarget()==null,100,10000);
                Execution.delayUntil(()->player.getHealthGauge()==null,100,10000);
                WorldOverview worldToHopTo = Worlds.newQuery().free().filter(i->!i.isPVP() && !i.isMembersOnly() && !i.isLegacyOnly() && i.getId() != Worlds.getCurrent()).results().random();
                if (worldToHopTo!=null && WorldHop.hopTo(worldToHopTo) && world != Worlds.getCurrent()) {
                    hops++;
                    Execution.delayUntil(()->Skill.ATTACK.getBaseLevel()==attack,1000,10000);
                }
                return true;
            }
        }
        return false;
    }
}
