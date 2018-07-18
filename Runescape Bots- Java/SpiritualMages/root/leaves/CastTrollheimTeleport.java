package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CastTrollheimTeleport extends LeafTask {

    private OpiaSpiritualMages bot;
    private Area trollheim=new Area.Circular(new Coordinate(2889,3671,0),80);

    public CastTrollheimTeleport(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("CastTrollheimTeleport");
        bot.setCurrentTask("Casting Teleport");
        try {
            if (Bank.isOpen()) {
                Bank.close();
                Execution.delayUntil(() -> !Bank.isOpen(), 1000, 3000);
            } else {
                ActionBar.Slot trollehimTele = ActionBar.newQuery().names("Trollheim Teleport").results().first();
                if (trollehimTele != null) {
                    if (trollehimTele.activate(true)) {
                        Execution.delay(2000, 3000);
                    } else {
                        if (Powers.Magic.TROLLHEIM_TELEPORT.activate()) {
                            Execution.delayUntil(() -> Players.getLocal() != null && trollheim.contains(Players.getLocal()), 5000, 15000);
                        }
                    }
                } else {
                    if (Powers.Magic.TROLLHEIM_TELEPORT.activate()) {
                        Execution.delayUntil(() -> Players.getLocal() != null && trollheim.contains(Players.getLocal()), 5000, 15000);
                    }
                }
                bot.getUtils().stuckCheck(3);
            }
        }catch(Exception e) {
            Environment.getLogger().debug("Failed to teleport to Trollheim");
        }
    }
}
