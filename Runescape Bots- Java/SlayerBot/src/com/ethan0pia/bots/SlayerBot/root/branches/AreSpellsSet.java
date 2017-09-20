package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.leaves.SetSpell;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreSpellsSet extends BranchTask {

    private AreWeGeared areWeGeared;
    private SetSpell setSpell;
    private OpiaSlayer bot;

    public AreSpellsSet(OpiaSlayer bot){
        this.bot=bot;
        areWeGeared = new AreWeGeared(bot);
        setSpell = new SetSpell(bot);
    }

    @Override
    public boolean validate() {
        if(Varbits.load(36325)!=null) {
            bot.setSpell(Varbits.load(36325).getValue());
        }
        boolean noSpell = bot.getMonster().getSpellType().equalsIgnoreCase("None");
        return (bot.isSpellSelectTask() && !noSpell) || (!noSpell && Varbits.load(36325).getValue()==0);
    }

    @Override
    public TreeTask failureTask() {
        return areWeGeared;
    }

    @Override
    public TreeTask successTask() {
        return setSpell;
    }
}

