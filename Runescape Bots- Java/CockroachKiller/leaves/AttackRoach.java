package com.ethan0pia.bots.CockroachKiller.leaves;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
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
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.ArrayList;
import java.util.List;

/**
 * NOTES:
 * 
 */
public class AttackRoach extends LeafTask {

    private Roach bot;
    private int hops = 0;
    private List<Npc> mobs = new ArrayList<>();
    private int fails = 0;
    private Area.Rectangular mobArea = new Area.Rectangular(new Coordinate(3145,4273,3),new Coordinate(3160,4281,3));

    public AttackRoach(Roach bot){
        this.bot=bot;
    }

    //5881 roach death number

    @Override
    public void execute() {
        Environment.getLogger().debug("AttackMob");
        try {
            Player player = bot.getPlayer();
            //animation id 8789 is death animation
            if(player.getAnimationId()!=-1 || (player.getTarget()!=null && player.getTarget().getAnimationId()!=8789)){
                Execution.delayWhile(()->Health.getCurrentPercent()>40 && player.getAnimationId()!=-1 || (player.getTarget()!=null && player.getTarget().getAnimationId()!=8789),200,15000);
            }else {
                Npc mob = Npcs.newQuery().actions("Attack").within(mobArea).filter(i -> i.getAnimationId() != 8789).targeting(player).results().nearest();
                boolean hopping = false;
                if (mob == null) {
                    hopping = hop(player);
                    Environment.getLogger().debug("Mob is null");
                    if (mobs.isEmpty()) {
                        mobs = Npcs.newQuery().actions("Attack").within(mobArea).filter(i -> i.getAnimationId() != 8789).names("Cockroach soldier").filter(i -> i.getTarget() == null).results().asList();
                    }
                    if (!mobs.isEmpty()) {
                        for (int i = 0; i < mobs.size(); i++) {
                            if (!mobs.get(0).isValid() || mobs.get(0).getAnimationId() == 8789) {
                                mobs.remove(0);
                            } else {
                                mob = mobs.get(0);
                                break;
                            }

                            if (mobs.isEmpty()) {
                                break;
                            }
                        }
                    }else{
                        Execution.delayWhile(()->Npcs.newQuery().actions("Attack").within(mobArea).filter(i -> i.getAnimationId() != 8789).names("Cockroach soldier").filter(i -> i.getTarget() == null).results().isEmpty(),200,5000);
                    }
                }
                if (!hopping && mob != null && mob.isValid()) {
                    if (Camera.getPitch() < 0.5) {
                        Camera.concurrentlyTurnTo(Random.nextDouble(0.5, 0.9));
                    }
                    if (!mob.isVisible()) {
                        Camera.concurrentlyTurnTo(mob, Random.nextDouble(0.5, 0.9));
                    }
                    if (!player.isMoving()) {
                        if (mob.interact("Attack")) {
                            Execution.delayUntil(() -> player.getTarget() != null, 100, 3000);
                            fails = 0;
                        } else if (fails > 5) {
                            Inventory.getItemIn(Random.nextInt(0, 27)).hover();
                        } else {
                            fails++;
                        }
                    }
                }
            }
        }catch(Exception e){
            Environment.getLogger().debug("Failed to attack monster");
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
