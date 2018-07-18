package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class SetFoodBool extends LeafTask {

    private OpiaSpiritualMages bot;

    public SetFoodBool(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("SetFoodBool");
        bot.setCurrentTask("Need Food");
        bot.setNeedBank(true);
        bot.getUtils().stuckCheck(12);
    }
}
