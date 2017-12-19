package com.ethan0pia.bots.telegrabLeveler.leaves;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class SetSpell extends LeafTask {

    private TelegrabLeveler bot;

    public SetSpell(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            if (InterfaceWindows.getMagic().isOpen()) {
                if (Skill.MAGIC.getBaseLevel() > 16) {
                    if (Powers.Magic.AIR_BOLT.activate()) {
                        Execution.delay(500, 1000);
                    }
                } else {
                    if (Powers.Magic.AIR_STRIKE.activate()) {
                        Execution.delay(500, 1000);
                    }
                }
            } else {
                if(!InterfaceWindows.getMagic().open()){
                    Keyboard.typeKey("u");
                    Execution.delay(500, 1000);
                }else{
                    Execution.delay(500, 1000);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
