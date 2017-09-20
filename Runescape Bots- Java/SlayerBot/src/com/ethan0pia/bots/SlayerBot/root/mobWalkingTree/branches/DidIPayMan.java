package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.PayMan;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.InteractGameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DidIPayMan extends BranchTask {

    private InteractGameObject interactgameobject;
    private PayMan payman;
    private OpiaSlayer bot;

    public DidIPayMan(OpiaSlayer bot){
        this.bot=bot;
        payman = new PayMan(bot);
        interactgameobject = new InteractGameObject(bot,"Dungeon entrance", "Enter");
    }


    @Override
    public boolean validate() {
        if(ChatDialog.getText()!=null && ChatDialog.getText().equalsIgnoreCase("You can't go in there without paying!")){
            bot.setPaid(false);
        }
        return bot.isPaid();
    }

    @Override
    public TreeTask failureTask() {
        return payman;
    }

    @Override
    public TreeTask successTask() {
        return interactgameobject;
    }
}
