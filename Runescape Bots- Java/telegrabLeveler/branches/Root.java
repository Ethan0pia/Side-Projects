package com.ethan0pia.bots.telegrabLeveler.branches;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.telegrabLeveler.leaves.EmptyLeaf;
import com.github.sheigutn.pushbullet.items.chat.Chat;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

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
