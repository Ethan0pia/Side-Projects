package com.ethan0pia.bots.SlayerBot.root;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.branches.CheckHealth;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
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
    private OpiaSpiritualMages bot;
    private int count=0;

    private int startSlayXP, startAttXP, startDefXP, startStrXP, startRangeXP, startMageXP, startHpXP, startPrayXP, startDef, startAtt,
            startSlay, startStr, startMage, startRange, startHp, startPray;

    public Root(OpiaSpiritualMages bot){
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
        }
    }


    @Override
    public boolean validate() {
        bot.setPlayer(Players.getLocal());

        return bot.getPlayer()==null;
    }

    @Override
    public TreeTask failureTask() {
        if(!bot.getStopWatch().isRunning()) {
            bot.getStopWatch().start();
        }
        if(count==5) {
            updateUi();
            count=0;
        }else{
            ++count;
        }
        if (LootInventory.isEnabled()) {
            ClientUI.showAlert("Loot inventory is enabled. Please turn it off.");
            Environment.getLogger().warn("Loot inventory is enabled. Please turn it off.");
            bot.stop("Failed");
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
                mage - startMage, range - startRange, hp - startHp, pray - startPray);

        bot.getInfoUI().update1( bot.getGpGained(), gph, att, attXP, slay, slayXP, str, strXP, def, defXP
                , mage, mageXP, range, rangeXP, hp, hpXP, pray, prayXP, "Spiritual Mages",
                hpToLvl, slayToLvl, attToLvl, strToLvl, defToLvl, rangeToLvl, mageToLvl, prayToLvl);

        Platform.runLater(() -> bot.getInfoUI().update2());
    }
}
