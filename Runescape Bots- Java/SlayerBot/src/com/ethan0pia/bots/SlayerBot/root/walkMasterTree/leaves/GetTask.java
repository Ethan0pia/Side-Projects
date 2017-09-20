package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Talks to master to get task and sets bank boolean to false
 */
public class GetTask extends LeafTask {

    private OpiaSlayer bot;

    public GetTask(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Npc master = Npcs.getLoaded(bot.getMaster()).nearest();
        if(master!=null){
            if(!master.interact("Get-task")){
                Camera.concurrentlyTurnTo(master);
            }
            if(ChatDialog.getText()!=null && ChatDialog.getContinue()!=null){
                if(!ChatDialog.getContinue().select(true)){
                    ChatDialog.getContinue().select(false);
                }
            }
            Execution.delayUntil(()-> Varbits.load(7917).getValue()!=0,1000,3000);
            if(ChatDialog.getText()!=null && !ChatDialog.getOptions().isEmpty()){
                if(!ChatDialog.getOption(1).select(true)){
                    ChatDialog.getOption(1).select(false);
                }
            }
            Execution.delayUntil(()-> Varbits.load(7917).getValue()!=0,1000,3000);
        }
        bot.setBankBool(false);
        bot.setSpellSelectTask(true);
        bot.getUtils().stuckCheck("am");
    }
}
