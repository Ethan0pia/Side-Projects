package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class SetSpell extends LeafTask {

    private OpiaWineGrabberUltra bot;

    public SetSpell(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("SetSpell");
            bot.setCurrentTask("Setting the spell.");
            if (InterfaceWindows.getMagic().isOpen()) {
                if (Skill.MAGIC.getBaseLevel() > 16) {
                    if (Powers.Magic.AIR_BOLT.activate()) {
                        Execution.delayUntil(()->46 == Varbits.load(36325).getValue() || 47 == Varbits.load(36325).getValue(),5000);
                    }
                } else {
                    if (Powers.Magic.AIR_STRIKE.activate()) {
                        Execution.delayUntil(()->28 == Varbits.load(36325).getValue() || 29 == Varbits.load(36325).getValue(),5000);
                    }
                }
            } else {
                if(!InterfaceWindows.getMagic().open()){
                    Keyboard.typeKey("u");
                    Execution.delay(1000, 2000);
                }else{
                    Execution.delay(1000, 2000);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
