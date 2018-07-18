package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.ethan0pia.bots.TrollKiller.leaves.SetSpell;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsSpellSet extends BranchTask {

    private IsAirStaffEquipped isAirStaffEquipped;
    private SetSpell setspell;
    private TrollKiller bot;

    public IsSpellSet(TrollKiller bot){
        this.bot=bot;
        isAirStaffEquipped = new IsAirStaffEquipped(bot);
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
        return isAirStaffEquipped;
    }
}
