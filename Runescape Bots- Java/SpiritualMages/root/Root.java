package com.ethan0pia.bots.SpiritualMages.root;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.ethan0pia.bots.SpiritualMages.root.branches.ShouldWeBreak;
import com.ethan0pia.bots.SpiritualMages.root.leaves.UseQuickTeleport;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.rs3.local.hud.interfaces.LootInventory;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SpiritualMages.root.leaves.EmptyLeaf;
import javafx.application.Platform;

import java.util.concurrent.TimeUnit;

/**
 * NOTES: done
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class Root extends BranchTask {

    private ShouldWeBreak shouldWeBreak;
    private EmptyLeaf emptyLeaf;
    private OpiaSpiritualMages bot;
    private boolean first = true;
    private int attXP = 0;
    private int defXP = 0;
    private int strXP = 0;
    private int rangeXP = 0;
    private int mageXP = 0;
    private int hpXP = 0;
    private int prayXP = 0;
    private int gph = 0;

    private int startAttXP, startDefXP, startStrXP, startRangeXP, startMageXP, startHpXP, startPrayXP;

    public Root(OpiaSpiritualMages bot){
        this.bot=bot;
        shouldWeBreak = new ShouldWeBreak(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("root");
        bot.setPlayer(Players.getLocal());
        return bot.getPlayer()!=null && bot.getGo() || !GameEvents.Universal.LOBBY_HANDLER.isEnabled();
    }

    @Override
    public TreeTask successTask() {
        if(startHpXP==0) {
            if (!bot.getStopWatch().isRunning()) {
                bot.getStopWatch().start();
            }
            if (LootInventory.isEnabled()) {
                ClientUI.showAlert("Loot inventory is enabled. Please turn it off.");
                Environment.getLogger().warn("Loot inventory is enabled. Please turn it off.");
                bot.stop("Failed");
            }
            startAttXP = Skill.ATTACK.getExperience();
            startDefXP = Skill.DEFENCE.getExperience();
            startStrXP = Skill.STRENGTH.getExperience();
            startRangeXP = Skill.RANGED.getExperience();
            startMageXP = Skill.MAGIC.getExperience();
            startHpXP = Skill.CONSTITUTION.getExperience();
            startPrayXP = Skill.PRAYER.getExperience();
            return emptyLeaf;
        }
        if(first){
            new LoopingThread(() -> Platform.runLater(()->bot.getInfoUI().update1( bot.getGpGained(), gph, attXP, strXP, defXP
                    , mageXP, rangeXP, hpXP, prayXP, bot.getCurrentTask(), bot.getStopWatch().getRuntime(TimeUnit.MINUTES))), 1000).start();
            first=false;
        }
        updateUi();
        return shouldWeBreak;
    }

    @Override
    public TreeTask failureTask() {
        return emptyLeaf;
    }

    private void updateUi(){
        if(bot.getStopWatch().getRuntime(TimeUnit.HOURS)>bot.getHour()){
            bot.setHops(0);
        }
        attXP = Skill.ATTACK.getExperience() - startAttXP;
        defXP = Skill.DEFENCE.getExperience() - startDefXP;
        strXP = Skill.STRENGTH.getExperience() - startStrXP;
        rangeXP = Skill.RANGED.getExperience() - startRangeXP;
        mageXP = Skill.MAGIC.getExperience() - startMageXP;
        hpXP = Skill.CONSTITUTION.getExperience() - startHpXP;
        prayXP = Skill.PRAYER.getExperience() - startPrayXP;
        gph = (int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), bot.getGpGained());
    }
}
