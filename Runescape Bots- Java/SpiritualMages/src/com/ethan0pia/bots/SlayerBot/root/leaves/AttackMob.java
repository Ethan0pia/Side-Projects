package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Attack the monster.
 */
public class AttackMob extends LeafTask {

    private OpiaSpiritualMages bot;
    private Area mobArea = new Area.Circular(new Coordinate(2887,5358,0), 12);

    public AttackMob(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Npc mob;
        if (bot.getPlayer().getTarget()==null) {
            Npc targetMob = Npcs.newQuery().targeting(bot.getPlayer()).actions("Attack").within(new Area.Circular(bot.getPlayer().getPosition(), 12)).names("Spiritual mage").filter(i -> (i.getSpotAnimationIds().isEmpty() || !(4100 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4200))).results().nearest();
            Npc nonTarget = Npcs.newQuery().targeting(bot.getPlayer()).actions("Attack").within(new Area.Circular(bot.getPlayer().getPosition(), 12)).within(mobArea).filter(i -> (i.getSpotAnimationIds().isEmpty() || !(4100 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4200))).results().nearest();
            if (targetMob != null && bot.getPlayer().getTarget() == null) {
                mob = targetMob;

            } else if(nonTarget!=null) {
                mob = nonTarget;

            }else{
                mob = Npcs.newQuery().actions("Attack").names("Spiritual mage").within(mobArea).filter(i-> (i.getSpotAnimationIds().isEmpty() && i.getAnimationId()==-1) || (!(4180 < i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4190) && i.getAnimationId()==-1 && i.getTarget()==null)).results().sortByDistance().limit(0, 2).random();//4187 is death spot animation
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
                        bot.getUtils().shouldWeAlch();
                        Execution.delay(1000, 2000);
                    }
                }
            }
        }
        bot.getUtils().stuckCheck(15);
    }
}
