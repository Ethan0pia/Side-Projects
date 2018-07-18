package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.ethan0pia.bots.TrollKiller.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Root extends BranchTask {

    private EmptyLeaf emptyleaf;
    private AreWeAtAttackLevel areWeAtAttackLevel;
    private TrollKiller bot;

    public Root(TrollKiller bot){
        this.bot=bot;
        emptyleaf = new EmptyLeaf(bot);
        areWeAtAttackLevel = new AreWeAtAttackLevel(bot);
    }

    @Override
    public boolean validate() {
        //for "Don't use wind bolt anymore" popup.
        if(ChatDialog.getContinue()!=null){
            ChatDialog.getContinue().select(false);
        }
        bot.setPlayer(Players.getLocal());
        return bot.getPlayer()==null && !bot.isGo();
    }

    @Override
    public TreeTask failureTask() {
        bot.setAttack(Skill.ATTACK.getBaseLevel());
        bot.setMage(Skill.MAGIC.getBaseLevel());
        bot.setRange(Skill.RANGED.getBaseLevel());
        return areWeAtAttackLevel;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
