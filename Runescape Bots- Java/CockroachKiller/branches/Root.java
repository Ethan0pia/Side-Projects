package com.ethan0pia.bots.CockroachKiller.branches;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.CockroachKiller.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Root extends BranchTask {

    private EmptyLeaf emptyleaf;
    private AreWeAtLevel areWeAtLevel;
    private Roach bot;

    public Root(Roach bot){
        this.bot=bot;
        emptyleaf = new EmptyLeaf(bot);
        areWeAtLevel = new AreWeAtLevel(bot);
    }

    @Override
    public boolean validate() {
        Environment.getLogger().debug("9");
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
        return areWeAtLevel;
    }

    @Override
    public TreeTask successTask() {

        return emptyleaf;
    }
}
