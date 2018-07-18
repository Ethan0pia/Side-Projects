package com.ethan0pia.bots.WineGrabberUltra;

import com.ethan0pia.bots.WineGrabberUltra.grabber.branches.DoWeNeedToBank;
import com.ethan0pia.bots.WineGrabberUltra.leveler.branches.AreWeAtMagicLevel;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class IsFaladorLodeActivated extends BranchTask {

    private DoWeNeedToBank doWeNeedToBank;
    private AreWeAtMagicLevel areWeAtMagicLevel;
    private OpiaWineGrabberUltra bot;

    public IsFaladorLodeActivated(OpiaWineGrabberUltra bot){
        this.bot=bot;
        doWeNeedToBank = new DoWeNeedToBank(bot);
        areWeAtMagicLevel = new AreWeAtMagicLevel(bot);
    }

    @Override
    public boolean validate() {
        if(!Lodestone.FALADOR.isActivated() || Skill.MAGIC.getBaseLevel()<33) {
            //for "Don't use wind bolt anymore" popup.
            ChatDialog.Continue chat = ChatDialog.getContinue();
            if(chat!=null){
                chat.select();
            }
            bot.setCurrentMagicLevel(Skill.MAGIC.getBaseLevel());
            return false;
        }
        return true;
    }

    @Override
    public TreeTask failureTask() {

        return areWeAtMagicLevel;
    }

    @Override
    public TreeTask successTask() {
        return doWeNeedToBank;
    }
}
