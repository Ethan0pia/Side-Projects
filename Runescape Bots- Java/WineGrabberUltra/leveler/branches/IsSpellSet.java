package com.ethan0pia.bots.WineGrabberUltra.leveler.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.AttackTroll;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.SetSpell;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsSpellSet extends BranchTask {

    private AttackTroll attacktrollbrute;
    private SetSpell setspell;
    private OpiaWineGrabberUltra bot;

    public IsSpellSet(OpiaWineGrabberUltra bot){
        this.bot=bot;
        attacktrollbrute = new AttackTroll(bot, "Troll brute", true);
        setspell = new SetSpell(bot);
    }


    @Override
    public boolean validate() {
        int spellId = 0;
        try {
            spellId = Varbits.load(36325).getValue();
        }catch(Exception e){
            e.printStackTrace();
        }
        return (Skill.MAGIC.getBaseLevel()<17 && (spellId== 28 || spellId == 29))||(Skill.MAGIC.getBaseLevel() > 16 && (spellId==46 || spellId == 47));
    }

    @Override
    public TreeTask failureTask() {
        return setspell;
    }

    @Override
    public TreeTask successTask() {
        return attacktrollbrute;
    }
}
