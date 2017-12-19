package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CastTrollheimTeleport extends LeafTask {

    private OpiaSpiritualMages bot;

    public CastTrollheimTeleport(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        ActionBar.Slot trollehimTele = ActionBar.newQuery().names("Trollheim Teleport").results().first();
        if(trollehimTele!=null){
            if(trollehimTele.activate(true)){
                Execution.delay(2000,3000);
            }else{
                if(Powers.Magic.TROLLHEIM_TELEPORT.activate()){
                    Execution.delay(2000,3000);
                }
            }
        }else{
            if(Powers.Magic.TROLLHEIM_TELEPORT.activate()) {
                Execution.delay(2000, 3000);
            }
        }
        bot.getUtils().stuckCheck(3);
    }
}
