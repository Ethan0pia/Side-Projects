package com.ethan0pia.bots.DuelSlave.branches;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.ethan0pia.bots.DuelSlave.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

/**
 * NOTES:
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class Root extends BranchTask {

    private EmptyLeaf emptyleaf;
    private AreWeInsideArena amiinaduel;
    private DuelingSlave bot;
    private Coordinate oldLocation;
    private Area duelArea = new Area.Rectangular(new Coordinate(3361,3260,0 ), new Coordinate(3391,3242,0));

    public Root(DuelingSlave bot){
        this.bot=bot;
        emptyleaf = new EmptyLeaf(bot);
        amiinaduel = new AreWeInsideArena(bot);
    }

    @Override
    public boolean validate() {

        bot.setPlayer(Players.getLocal());
        if(!bot.getStopWatch().isRunning()) {
            List<String> playersStr = new ArrayList<>();
            List<Player> players = Players.newQuery().combatLevels(3, 200).results().asList();

            for (Player i : players) {
                if (i != null && !i.getName().equals(bot.getPlayer().getName())) {
                    playersStr.add(i.getName());
                }
            }
            bot.setPlayers(playersStr);
        }

        return bot.isGuiWait() || bot.getPlayer()==null;
    }

    @Override
    public TreeTask failureTask() {
        StopWatch mainSW = bot.getStopWatch();
        if(mainSW!=null && !mainSW.isRunning()) {
            mainSW.start();
            new LoopingThread(() -> Platform.runLater(() -> bot.getInfoUI().update()), 1000).start();
        }
        //if it was in the duel area, but now it isn't, then it completed a duel.
        if(oldLocation==null && bot.getPlayer()!=null){
            oldLocation=bot.getPlayer().getPosition();
        }

        Coordinate currLocation=bot.getPlayer().getPosition();
        if(duelArea.contains(oldLocation) && !duelArea.contains(currLocation)){
            bot.setDuelsCompleted(bot.getDuelsCompleted()+1);
            bot.getDuelWatch().stop();
            bot.getDuelWatch().reset();
        }
        oldLocation=currLocation;

        return amiinaduel;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
