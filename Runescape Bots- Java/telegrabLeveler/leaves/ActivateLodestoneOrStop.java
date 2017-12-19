package com.ethan0pia.bots.telegrabLeveler.leaves;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ActivateLodestoneOrStop extends LeafTask {

    private TelegrabLeveler bot;
    private Area edgevilleLode = new Area.Circular(Lodestone.EDGEVILLE.getPosition(),6);
    private Area fallyLode = new Area.Circular(new Coordinate(2967,3406,0),4);

    public ActivateLodestoneOrStop(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            if (!Lodestone.FALADOR.isActivated()) {
                if (!fallyLode.contains(bot.getPlayer())) {
                    WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(fallyLode.getCenter());
                    if (path != null) {
                        path.step();
                    }
                } else {
                    if (Lodestone.FALADOR.getPosition().click()) {
                        Execution.delay(5000, 7000);
                    }
                }
            } else if (!Lodestone.EDGEVILLE.isActivated()) {
                if (!edgevilleLode.contains(bot.getPlayer())) {
                    BresenhamPath path = BresenhamPath.buildTo(Lodestone.EDGEVILLE.getPosition());
                    path.step();
                } else {
                    if (Lodestone.EDGEVILLE.getPosition().click()) {
                        Execution.delay(5000, 7000);
                    }
                }
            } else {
                if (Lodestone.FALADOR.teleport()) {
                    bot.stop("Tasks completed");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
