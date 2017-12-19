package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class SetSpell extends LeafTask {

    OpiaSlayer bot;

    public SetSpell(OpiaSlayer bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        if(Bank.isOpen()) {
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(), 1000, 2000);
        }
        pickSpell();
        bot.getUtils().stuckCheck(26);
    }

    private void pickSpell() {
        int mageLvl = Skill.MAGIC.getCurrentLevel();
        int spell=bot.getMonster().getSpellType();
        switch(spell) {
            case 1:
                if (mageLvl > 80) {
                    helper(146, Powers.Magic.AIR_SURGE);
                } else if (mageLvl > 61) {
                    helper(116, Powers.Magic.AIR_WAVE);
                } else if (mageLvl > 40) {
                    helper(74, Powers.Magic.AIR_BLAST);
                } else if (mageLvl > 16) {
                    helper(46, Powers.Magic.AIR_BOLT);
                } else {
                    helper(28, Powers.Magic.AIR_STRIKE);
                }
                break;
            case 2:
                if (mageLvl > 89) {
                    helper(152, Powers.Magic.EARTH_SURGE);
                } else if (mageLvl > 69) {
                    helper(130, Powers.Magic.EARTH_WAVE);
                } else if (mageLvl > 52) {
                    helper(92, Powers.Magic.EARTH_BLAST);
                } else if (mageLvl > 28) {
                    helper(60, Powers.Magic.EARTH_BOLT);
                } else if (mageLvl > 8) {
                    helper(36, Powers.Magic.EARTH_STRIKE);
                } else {
                    helper(28, Powers.Magic.AIR_STRIKE);
                }
                break;
            case 3:
                if (mageLvl > 84) {
                    helper(156, Powers.Magic.WATER_SURGE);
                } else if (mageLvl > 64) {
                    helper(122, Powers.Magic.WATER_WAVE);
                } else if (mageLvl > 46) {
                    helper(80, Powers.Magic.WATER_BLAST);
                } else if (mageLvl > 22) {
                    helper(54, Powers.Magic.WATER_BOLT);
                } else if (mageLvl > 4) {
                    helper(32, Powers.Magic.WATER_STRIKE);
                } else {
                    helper(28, Powers.Magic.AIR_STRIKE);
                }
                break;
            case 4:
                if (mageLvl > 94) {
                    helper(160, Powers.Magic.FIRE_SURGE);
                } else if (mageLvl > 74) {
                    helper(136, Powers.Magic.FIRE_WAVE);
                } else if (mageLvl > 58) {
                    helper(102, Powers.Magic.FIRE_BLAST);
                } else if (mageLvl > 34) {
                    helper(66, Powers.Magic.FIRE_BOLT);
                } else if (mageLvl > 12) {
                    helper(42, Powers.Magic.FIRE_STRIKE);
                } else {
                    helper(28, Powers.Magic.AIR_STRIKE);
                }
                break;
        }
    }

    private void helper(int spellStr, Powers.Magic spell){
        if(!InterfaceWindows.getMagic().isOpen()){
            InterfaceWindows.getMagic().open();
            Execution.delayUntil(()->InterfaceWindows.getMagic().isOpen(),2000,4000);
        }

        if(bot.getSpell()!=spellStr || Varbits.load(36325).getValue()==0) {
            if (spell.activate()) {
                bot.setSpell(spellStr);
                bot.setSpellSelectTask(false);
                Execution.delay(1000, 2000);
                if(Equipment.getItemIn(5)!=null){
                    if(ChatDialog.getOption(3)!=null) {
                        ChatDialog.getOption(3).select(false);
                    }
                }
            }
        }else{
            bot.setSpellSelectTask(false);
        }

        InterfaceWindows.getInventory().isOpen();
    }
}
