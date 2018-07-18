package com.ethan0pia.bots.TelegrabLeveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class AttackTroll extends LeafTask {

    private TelegrabLeveler bot;
    private String name;

    public AttackTroll(TelegrabLeveler bot, String name){
        this.bot=bot;
        this.name=name;
    }


    @Override
    public void execute() {
        try {
            Npc mob;
            Player player = bot.getPlayer();
            Actor target = player.getTarget();
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
                        if (mob.interact("Attack")) {
                            Execution.delayUntil(() -> player.getAnimationId() != -1, 1000);
                            Execution.delayUntil(() -> player.getAnimationId() == -1, 2000);
                        }
                    } else {
                        Camera.concurrentlyTurnTo(mob, Random.nextDouble(0.6,0.9));
                        Execution.delayUntil(mob::isVisible, 2000);
                    }
                }
            } else {
                Execution.delayUntil(() -> player.getAnimationId() == -1, 2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
