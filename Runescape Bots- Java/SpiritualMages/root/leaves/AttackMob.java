package com.ethan0pia.bots.SpiritualMages.root.leaves;

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
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.ArrayList;
import java.util.List;

/**
 * NOTES:
 * Attack the monster.
 */
public class AttackMob extends LeafTask {

    private OpiaSpiritualMages bot;
    private Area mobArea = new Area.Rectangular(new Coordinate(2880,5348,0), new Coordinate(2897,5366,0));
    private List<Npc> mobs = new ArrayList<>();
    private int fails = 0;

    public AttackMob(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("AttackMob");
        bot.setCurrentTask("Attacking Monster");
        try {
            Player player = bot.getPlayer();
            if (Equipment.getItemIn(Equipment.Slot.AMMUNITION.getIndex()) != null && !bot.isUsingAmmo()) {
                bot.setUsingAmmo(true);
            }

            if (bot.getWeapon() == null) {
                bot.setWeapon(Equipment.getItemIn(Equipment.Slot.WEAPON.getIndex()).getDefinition().getName());
            } else if (Equipment.getItems(bot.getWeapon()).isEmpty()) {
                SpriteItem weapon = Inventory.getItems(bot.getWeapon()).first();
                if (weapon != null) {
                    weapon.interact("Wield");
                    Execution.delayUntil(() -> !Equipment.getItems(bot.getWeapon()).isEmpty(), 500, 1000);
                } else {
                    ClientUI.showAlert("Weapon is missing!");
                    bot.stop("Weapon missing.");
                }
            } else {
                bot.setCurrentTask("Attacking Monster");
                //animation id 836 is death animation
                Npc mob = Npcs.newQuery().actions("Attack").within(mobArea).filter(i -> i.getAnimationId() != 836).targeting(player).results().nearest();
                boolean hopping = false;
                if (mob == null) {
                    hopping = hop(player);
                    Environment.getLogger().debug("Mob is null");
                    if (mobs.isEmpty()) {
                        mobs = Npcs.newQuery().actions("Attack").within(mobArea).filter(i -> i.getAnimationId() != 836).names("Spiritual mage").filter(i -> i.getTarget() == null).results().asList();
                    }
                    if (!mobs.isEmpty()) {
                        for (int i = 0; i < mobs.size(); i++) {
                            if (!mobs.get(0).isValid() || mobs.get(0).getAnimationId() == 836) {
                                mobs.remove(0);
                            } else {
                                mob = mobs.get(0);
                                break;
                            }

                            if (mobs.isEmpty()) {
                                break;
                            }
                        }
                    }
                }
                if (!hopping && mob != null && mob.isValid()) {
                    if(Camera.getPitch()<0.5){
                        Camera.concurrentlyTurnTo(Random.nextDouble(0.5,0.9));
                    }
                    if (!mob.isVisible()) {
                        Camera.concurrentlyTurnTo(mob,Random.nextDouble(0.5,0.9));
                    }
                    if (!player.isMoving()) {
                        if(mob.interact("Attack")) {
                            Execution.delayWhile(() -> player.getTarget() != null, 100, 3000);
                            fails=0;
                        }else if(fails>5){
                            Inventory.getItemIn(Random.nextInt(0,27)).hover();
                        }else{
                            fails++;
                        }
                    }
                }
            }
            bot.getUtils().stuckCheck(2);
        }catch(Exception e){
            Environment.getLogger().debug("Failed to attack monster");
        }
    }


    private boolean hop(Player player) {
        int currHops = bot.getHops();
        if (bot.isWorldHop()) {
            if (5 > currHops) {
                Player others = Players.newQuery().within(mobArea).filter(i -> !i.getName().equals(player.getName()) && (i.getTarget()!=null && i.getTarget().getName().equals("Spiritual mage"))).results().first();
                if (others != null) {
                    int attack = Skill.ATTACK.getBaseLevel();
                    Execution.delayUntil(()->player.getTarget()==null,100,10000);
                    Execution.delayUntil(()->player.getHealthGauge()==null,100,10000);
                    int world = Worlds.getCurrent();
                    WorldOverview worldToHopTo = Worlds.newQuery().member().filter(i -> !i.isPVP() && !i.isLegacyOnly() && i.getId() != Worlds.getCurrent() && !i.isVIP()).results().random();
                    if (worldToHopTo != null && WorldHop.hopTo(worldToHopTo) && world != Worlds.getCurrent()) {
                        Execution.delayUntil(()->Skill.ATTACK.getBaseLevel()==attack,1000,10000);
                        bot.setHops(currHops + 1);
                        mobs.clear();
                        bot.clearGroundItems();
                    }
                    return true;
                }
            }
        }
        return false;
    }

}
