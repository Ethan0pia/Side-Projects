package com.ethan0pia.bots.TelegrabLeveler.branches;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.TelegrabLeveler.leaves.EmptyLeaf;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.concurrent.TimeUnit;

/**
 * NOTES:
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class Root extends BranchTask {

    private EmptyLeaf emptyleaf;
    private AreWeAtMagicLevel areWeAtMagicLevel;
    private TelegrabLeveler bot;

    public Root(TelegrabLeveler bot){
        this.bot=bot;
        emptyleaf = new EmptyLeaf(bot);
        areWeAtMagicLevel = new AreWeAtMagicLevel(bot);
    }

    @Override
    public boolean validate() {
        //for "Don't use wind bolt anymore" popup.
        if(ChatDialog.getContinue()!=null){
            ChatDialog.getContinue().select(false);
        }
        bot.setCurrentMagicLevel(Skill.MAGIC.getBaseLevel());
        bot.setPlayer(Players.getLocal());

        if(bot.getStopWatch().getRuntime(TimeUnit.MINUTES) > 200){
            ClientUI.showAlert("The bot seems to have taken too long to complete.");
            Environment.getBot().stop("Time ran out.");
        }

        return bot.getPlayer()==null;
    }

    @Override
    public TreeTask failureTask() {
        return areWeAtMagicLevel;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
