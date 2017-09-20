package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.Environment;

/**
 * NOTES: done
 * Stops bot because no special item.
 */
public class StopBot extends LeafTask {

    private OpiaSlayer bot;

    public StopBot(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        ClientUI.showAlert("Error finding special item in bank.");
        Environment.getLogger().warn("Error finding a special item, stopping bot.");
        bot.stop();
    }
}
