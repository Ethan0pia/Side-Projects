package com.ethan0pia.bots.SlayerBot.root;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.SlayerMob;
import com.ethan0pia.bots.SlayerBot.root.branches.CheckHealth;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.Varps;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.rs3.local.hud.interfaces.LootInventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.leaves.EmptyLeaf;
import javafx.application.Platform;

import java.util.concurrent.TimeUnit;

/**
 * NOTES: done
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class Root extends BranchTask {

    private CheckHealth checkhealth;
    private EmptyLeaf emptyLeaf;
    private OpiaSlayer bot;

    private int startSlayXP, startAttXP, startDefXP, startStrXP, startRangeXP, startMageXP, startHpXP, startPrayXP, startDef, startAtt,
            startSlay, startStr, startMage, startRange, startHp, startPray, totalTask, oldCount, count, startCount, tasksComplete;
    private String[] gear;

    public Root(OpiaSlayer bot){
        this.bot=bot;
        checkhealth = new CheckHealth(bot);
        emptyLeaf = new EmptyLeaf(bot);

        Execution.delayUntil(() -> { bot.setPlayer(Players.getLocal()); return bot.getPlayer() != null;}, 1000,2000);
        for(int i=0;i<20;i++) {
            if(startHpXP!=0){
                break;
            }
            startSlayXP = Skill.SLAYER.getExperience();
            startAttXP = Skill.ATTACK.getExperience();
            startDefXP = Skill.DEFENCE.getExperience();
            startStrXP = Skill.STRENGTH.getExperience();
            startRangeXP = Skill.RANGED.getExperience();
            startMageXP = Skill.MAGIC.getExperience();
            startHpXP = Skill.CONSTITUTION.getExperience();
            startPrayXP = Skill.PRAYER.getExperience();

            startDef = Skill.DEFENCE.getCurrentLevel();
            startAtt = Skill.ATTACK.getCurrentLevel();
            startSlay = Skill.SLAYER.getCurrentLevel();
            startStr = Skill.STRENGTH.getCurrentLevel();
            startMage = Skill.MAGIC.getCurrentLevel();
            startRange = Skill.RANGED.getCurrentLevel();
            startHp = Skill.CONSTITUTION.getCurrentLevel();
            startPray = Skill.PRAYER.getCurrentLevel();
            count = Varbits.load(7917).getValue();
            totalTask=count;
            int task = Varbits.load(7923).getValue();
            bot.setMonster(SlayerMob.get(task));
            startCount = Varps.getAt(183).getValue();
            tasksComplete = 0;
        }
    }


    @Override
    public boolean validate() {
       if(bot.getPlayer()!=null) {
           setGear();
           bot.setPlayer(Players.getLocal());
       }
        return bot.getGuiWait() && bot.getPlayer()!=null;
    }

    private void helper(Equipment.Slot slot, int index) {
        if(Equipment.getItemIn(slot)!=null){
            gear[index]=Equipment.getItemIn(slot).getDefinition().getName();
        }else{
            gear[index]=" ";
        }
    }

    private void setGear(){
        gear = new String[12];
        helper(Equipment.Slot.HEAD,0);
        helper(Equipment.Slot.BODY,1);
        helper(Equipment.Slot.LEGS,2);
        helper(Equipment.Slot.FEET,3);
        helper(Equipment.Slot.HAND,4);
        helper(Equipment.Slot.CAPE,5);
        helper(Equipment.Slot.NECK,6);
        helper(Equipment.Slot.RING,7);
        helper(Equipment.Slot.SHIELD,8);
        helper(Equipment.Slot.AMMUNITION,9);
        helper(Equipment.Slot.POCKET,10);
        helper(Equipment.Slot.WEAPON,11);
    }

    public String[] getGear(){
       return gear;
    }

    @Override
    public TreeTask failureTask() {
        int task =Varbits.load(7923).getValue();
        if(!bot.getStopWatch().isRunning()) {
            Execution.delayUntil((()->bot.getMonster()!=null),10000);
            bot.getMonster().updateGear();
            bot.getStopWatch().start();
        }

        count = Varps.getAt(183).getValue();
        if ((count == 0 && startCount != 0 && tasksComplete == 0 && oldCount != 0) || (tasksComplete != 0 && count == 0 && oldCount != 0)) {
            tasksComplete++;
        }
        if(oldCount==0 && count!=0){
            Environment.getLogger().info(task);
            totalTask=count;
            bot.setMonster(SlayerMob.get(task));
        }
        oldCount = count;
        updateUi();

        if (LootInventory.isEnabled()) {
            ClientUI.showAlert("Loot inventory is enabled. Please turn it off.");
            Environment.getLogger().warn("Loot inventory is enabled. Please turn it off.");
            bot.stop();
        }
        
        return checkhealth;
    }

    @Override
    public TreeTask successTask() {
        return emptyLeaf;
    }

    private void updateUi(){

        double hpToLvl = (double) (100 - Skill.CONSTITUTION.getExperienceToNextLevelAsPercent()) / 100;
        double slayToLvl = (double) (100 - Skill.SLAYER.getExperienceToNextLevelAsPercent()) / 100;
        double attToLvl = (double) (100 - Skill.ATTACK.getExperienceToNextLevelAsPercent()) / 100;
        double strToLvl = (double) (100 - Skill.STRENGTH.getExperienceToNextLevelAsPercent()) / 100;
        double defToLvl = (double) (100 - Skill.DEFENCE.getExperienceToNextLevelAsPercent()) / 100;
        double rangeToLvl = (double) (100 - Skill.RANGED.getExperienceToNextLevelAsPercent()) / 100;
        double mageToLvl = (double) (100 - Skill.MAGIC.getExperienceToNextLevelAsPercent()) / 100;
        double prayToLvl = (double) (100 - Skill.PRAYER.getExperienceToNextLevelAsPercent()) / 100;

        int def = Skill.DEFENCE.getCurrentLevel();
        int att = Skill.ATTACK.getCurrentLevel();
        int slay = Skill.SLAYER.getCurrentLevel();
        int str = Skill.STRENGTH.getCurrentLevel();
        int mage = Skill.MAGIC.getCurrentLevel();
        int range = Skill.RANGED.getCurrentLevel();
        int hp = Skill.CONSTITUTION.getCurrentLevel();
        int pray = Skill.PRAYER.getCurrentLevel();

        int slayXP = Skill.SLAYER.getExperience() - startSlayXP;
        int attXP = Skill.ATTACK.getExperience() - startAttXP;
        int defXP = Skill.DEFENCE.getExperience() - startDefXP;
        int strXP = Skill.STRENGTH.getExperience() - startStrXP;
        int rangeXP = Skill.RANGED.getExperience() - startRangeXP;
        int mageXP = Skill.MAGIC.getExperience() - startMageXP;
        int hpXP = Skill.CONSTITUTION.getExperience() - startHpXP;
        int prayXP = Skill.PRAYER.getExperience() - startPrayXP;

        int gph = (int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), bot.getGpGained());

        bot.getInfoUI().update(att - startAtt, slay - startSlay, def - startDef, str - startStr,
                mage - startMage, range - startRange, hp - startHp, pray - startPray,
                count, totalTask);

        if (bot.getMonster()!=null && bot.getMonster().getTaskName() != null) {
            bot.getInfoUI().update1(tasksComplete, bot.getGpGained(), gph, att, attXP, slay, slayXP, str, strXP, def, defXP
                    , mage, mageXP, range, rangeXP, hp, hpXP, pray, prayXP, bot.getMonster().getTaskName(),
                    hpToLvl, slayToLvl, attToLvl, strToLvl, defToLvl, rangeToLvl, mageToLvl, prayToLvl);
        }
        Platform.runLater(() -> bot.getInfoUI().update2());
    }
}
