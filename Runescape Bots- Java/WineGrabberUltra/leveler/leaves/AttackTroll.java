package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.WorldOverview;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionWindow;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.ArrayList;
import java.util.List;

public class AttackTroll extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private String name;
    private boolean hopRoom;

    public AttackTroll(OpiaWineGrabberUltra bot, String name, boolean hopRoom){
        this.bot=bot;
        this.name=name;
        this.hopRoom = hopRoom;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("AttackRoach");
        bot.setCurrentTask("Attacking a troll.");
        try {
            Player player = bot.getPlayer();
            bot.setCurrentTask("Attacking Monster");
            if (!hop(player)) {
                if(Camera.getPitch()<0.6){
                    Camera.concurrentlyTurnTo(Random.nextDouble(0.75,0.9));
                }
                Npc mob;
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
                                Execution.delayWhile(() -> mob.isValid() && (mob.getSpotAnimationIds().isEmpty() || !(3500 < mob.getSpotAnimationIds().get(0) && mob.getSpotAnimationIds().get(0) < 4500)), 2000);
                            }
                        } else {
                            Camera.concurrentlyTurnTo(mob, Random.nextDouble(0.75,0.9));
                            Execution.delayUntil(mob::isVisible, 1000);
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
        }catch(Exception e){
            Environment.getLogger().debug("Failed to attack monster");
        }
    }

    private boolean hop(Player player) {
        int currHops = bot.getCurrHopsTrolls();
        if (hopRoom && bot.getMaxHopsTrolls() > currHops) {
            Player others = Players.newQuery().within(new Area.Circular(player.getPosition(),10)).filter(i -> i!=null && !i.getName().equals(player.getName()) && i.getStanceId()<18000).results().first();
            if (others != null) {
                int world = Worlds.getCurrent();
                int attack = Skill.ATTACK.getBaseLevel();
                Execution.delayUntil(()->player.getTarget()==null,100,10000);
                Execution.delayUntil(()->player.getHealthGauge()==null,100,10000);
                WorldOverview worldToHopTo = Worlds.newQuery().free().filter(i->!i.isPVP() && !i.isMembersOnly() && !i.isLegacyOnly() && i.getId() != Worlds.getCurrent()).results().random();
                if (worldToHopTo!=null && WorldHop.hopTo(worldToHopTo) && world != Worlds.getCurrent()) {
                    bot.setCurrHopsTrolls(currHops + 1);
                    Execution.delayUntil(()->Skill.ATTACK.getBaseLevel()==attack,1000,10000);
                }
                return true;
            }
        }
        return false;
    }
}
