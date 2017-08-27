package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.ethan0pia.bots.SlayerBot.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;

import java.util.concurrent.TimeUnit;

public class Root extends BranchTask {

    private HaveTask task = new HaveTask();
    private GoodAssSlayerBot Bot;

    private int startSlayXP;
    private int startAttXP;
    private int startDefXP;
    private int startStrXP;
    private int startRangeXP;
    private int startMageXP;
    private int startHpXP;
    private int startPrayXP;
    private int startDef;
    private int startAtt;
    private int startSlay;
    private int startStr;
    private int startMage;
    private int startRange;
    private int startHp;
    private int startPray;
    
    
    
    
    private Player player;

    private int count;
    private int startCount;
    private int tasksComplete;

    public Root(GoodAssSlayerBot Bot){
        this.Bot=Bot;

        Execution.delayUntil(() -> { player = Players.getLocal(); return player != null;});
        while(startSlayXP==0) {
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
            startCount = Varbits.load(7917).getValue();
            tasksComplete = 0;
        }
    }

    @Override
    public boolean validate() {

        Execution.delayUntil(() -> { player = Players.getLocal(); return player != null;});
        count = Varbits.load(7917).getValue();
        if((count==0&&startCount!=0&&tasksComplete==0)||(tasksComplete!=0&&count==0)){
            tasksComplete++;
        }


        double hpToLvl = (double)(100-Skill.CONSTITUTION.getExperienceToNextLevelAsPercent())/100;
        double slayToLvl = (double)(100-Skill.SLAYER.getExperienceToNextLevelAsPercent())/100;
        double attToLvl = (double)(100-Skill.ATTACK.getExperienceToNextLevelAsPercent())/100;
        double strToLvl = (double)(100-Skill.STRENGTH.getExperienceToNextLevelAsPercent())/100;
        double defToLvl = (double)(100-Skill.DEFENCE.getExperienceToNextLevelAsPercent())/100;
        double rangeToLvl = (double)(100-Skill.RANGED.getExperienceToNextLevelAsPercent())/100;
        double mageToLvl = (double)(100-Skill.MAGIC.getExperienceToNextLevelAsPercent())/100;
        double prayToLvl = (double)(100-Skill.PRAYER.getExperienceToNextLevelAsPercent())/100;

        int def = Skill.DEFENCE.getCurrentLevel();
        int att = Skill.ATTACK.getCurrentLevel();
        int slay = Skill.SLAYER.getCurrentLevel();
        int str = Skill.STRENGTH.getCurrentLevel();
        int mage = Skill.MAGIC.getCurrentLevel();
        int range = Skill.RANGED.getCurrentLevel();
        int hp = Skill.CONSTITUTION.getCurrentLevel();
        int pray = Skill.PRAYER.getCurrentLevel();

        Bot.infoUI.update(att-startAtt, slay-startSlay, def-startDef, str-startStr, mage-startMage, range-startRange, hp-startHp, pray-startPray, count);

        int slayXP = Skill.SLAYER.getExperience()-startSlayXP;
        int attXP=Skill.ATTACK.getExperience()-startAttXP;
        int defXP=Skill.DEFENCE.getExperience()-startDefXP;
        int strXP=Skill.STRENGTH.getExperience()-startStrXP;
        int rangeXP=Skill.RANGED.getExperience()-startRangeXP;
        int mageXP=Skill.MAGIC.getExperience()-startMageXP;
        int hpXP=Skill.CONSTITUTION.getExperience()-startHpXP;
        int prayXP=Skill.PRAYER.getExperience()-startPrayXP;

        String runTime = Bot.stopWatch.getRuntimeAsString();
        int task = Varbits.load(7923).getValue();

        int gph = (int) CommonMath.rate(TimeUnit.HOURS, Bot.stopWatch.getRuntime(), Bot.gpGained);
        Bot.infoUI.update1(tasksComplete, Bot.gpGained, gph, att,   attXP,   slay,   slayXP,   str,   strXP,   def,   defXP
                ,   mage,   mageXP,   range,   rangeXP,   hp,   hpXP,   pray,   prayXP, runTime, "current task",
                hpToLvl, slayToLvl, attToLvl, strToLvl, defToLvl, rangeToLvl, mageToLvl, prayToLvl);

        Platform.runLater(() -> Bot.infoUI.update2());





        return Bot.guiWait;
    }

    @Override
    public TreeTask failureTask() {
        return new EmptyLeaf();
    }

    @Override
    public TreeTask successTask() {

        if (Bot.stopWatch.getRuntime() > 120000)
            Bot.stop();

        // Return an empty task, we don't want to do anything while the user is still configuring the GUI
        return new EmptyLeaf();
    }
}
