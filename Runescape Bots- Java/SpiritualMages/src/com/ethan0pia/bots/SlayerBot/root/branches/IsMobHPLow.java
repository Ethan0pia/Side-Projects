package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks monster's HP
 */
public class IsMobHPLow extends BranchTask {

    private EmptyLeaf doesrequirekillingblow;
    private IsAdrenalineAt100 isAdrenalineAt100;
    private OpiaSpiritualMages bot;

    public IsMobHPLow(OpiaSpiritualMages bot){
        this.bot=bot;
        doesrequirekillingblow = new EmptyLeaf(bot);
        isAdrenalineAt100 = new IsAdrenalineAt100(bot);
    }

    @Override
    public boolean validate() {
        try {
            InterfaceComponent results = Interfaces.newQuery().containers(1490).filter(i->i.getId()==97648660).results().first();
            int mobHp = 1000;
            if(results!=null) {
                mobHp = Integer.parseInt(results.getText());
            }
            return mobHp<900 && mobHp!=200;
        }catch(Exception e){
            Environment.getLogger().debug("Failed to check mob HP");
            return false;
        }
    }

    @Override
    public TreeTask failureTask() {
        return isAdrenalineAt100;
    }

    @Override
    public TreeTask successTask() {
        return doesrequirekillingblow;
    }
}
