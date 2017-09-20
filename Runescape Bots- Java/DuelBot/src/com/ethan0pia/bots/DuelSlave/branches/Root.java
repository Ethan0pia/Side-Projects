package com.ethan0pia.bots.DuelSlave.branches;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.ethan0pia.bots.DuelSlave.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.entities.Player;
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
    private AmIInADuel amiinaduel;
    private DuelingSlave bot;
    private String currLocation;
    private String oldLocation;

    public Root(DuelingSlave bot){
        this.bot=bot;
        emptyleaf = new EmptyLeaf(bot);
        amiinaduel = new AmIInADuel(bot);
        currLocation="Start";
        oldLocation="Start";
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

        //checks and adds to duel count.
        if(bot.getDuelingLobby().contains(bot.getPlayer())){
            currLocation="Lobby";
        }else if(bot.getDuelArea().contains(bot.getPlayer())){
            currLocation="Duel";
        }else{
            currLocation="Other";
        }

        //if it was in the duel area, but now it isn't, then it completed a duel.
        if(oldLocation.equals("Duel") && !currLocation.equals("Duel")){
            bot.setDuelsCompleted(bot.getDuelsCompleted()+1);
        }
        oldLocation=currLocation;

        //waits until gui is settup and start is clicked
        return bot.isGuiWait();
    }

    @Override
    public TreeTask failureTask() {
        StopWatch mainSW = bot.getStopWatch();
        if(mainSW!=null && !mainSW.isRunning()) {
            mainSW.start();
            new LoopingThread(() -> Platform.runLater(() -> bot.getInfoUI().update()), 1000).start();
        }
        return amiinaduel;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
