package com.ethan0pia.bots.DuelSlave;

import com.ethan0pia.bots.DuelSlave.branches.Root;
import com.ethan0pia.bots.DuelSlave.ui.DuelingUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.listeners.ChatboxListener;
import com.runemate.game.api.script.framework.listeners.events.MessageEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.util.StopWatch;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class DuelingSlave extends TreeBot implements ChatboxListener, EmbeddableUI {

    private Area duelingLobby = new Area.Rectangular(new Coordinate(3331,3241,0), new Coordinate(3351,3223,0));
    private Area duelingLobbyCenter = new Area.Circular(duelingLobby.getCenter(),8);
    private Area duelArea = new Area.Rectangular(new Coordinate(3361,3260,0 ), new Coordinate(3391,3242,0));
    private boolean isMaster;
    private boolean guiWait;
    private String opponentsName;
    private int duelsCompleted;
    private Player player;
    private List<String> players;
    private volatile boolean sendChallenge=false;
    private volatile boolean sentChallenge=false;
    private StopWatch duelTimer;

    private DuelingUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;
    private StopWatch stopWatch = new StopWatch();

    public DuelingSlave(){
        setEmbeddableUI(this);
        opponentsName="";
        guiWait=true;
        duelsCompleted=0;
        players = new ArrayList<>();
        duelTimer=new StopWatch();
        getEventDispatcher().addListener(this);
    }

    @Override
    public TreeTask createRootTask()
    {
        return new Root(this);
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new DuelingUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onMessageReceived(MessageEvent event) {
        if(event.getSender()==null) {
            if (event.getSender() == null && event.getMessage().equals("Challenging " + opponentsName + "...")) {
                setSentChallenge(true);
            }
        }else if(event.getSender().equals(getOpponentsName())) {
                sendChallenge = true;
        }
    }

    @Override
    public void onStart(String... args){
        setLoopDelay(300, 800);
    }

    public Area getDuelingLobbyCenter() {
        return duelingLobbyCenter;
    }

    public boolean isSendChallenge() {
        return sendChallenge;
    }

    public void setSendChallenge(boolean sendChallenge) {
        this.sendChallenge = sendChallenge;
    }

    public boolean isSentChallenge() {
        return sentChallenge;
    }

    public void setSentChallenge(boolean sentChallenge) {
        this.sentChallenge = sentChallenge;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public int getDuelsCompleted() {
        return duelsCompleted;
    }

    public void setDuelsCompleted(int duelsCompleted) {
        this.duelsCompleted = duelsCompleted;
    }

    public Player getPlayer() {
        return player;
    }

    public Area getDuelingLobby() {
        return duelingLobby;
    }

    public Area getDuelArea() {
        return duelArea;
    }

    public boolean isGuiWait() {
        return guiWait;
    }

    public void setGuiWait(boolean guiWait) {
        this.guiWait = guiWait;
    }

    public String getOpponentsName() {
        return opponentsName;
    }

    public void setOpponentsName(String opponentsName) {
        this.opponentsName = opponentsName;
    }

    public DuelingUI getInfoUI() {
        return infoUI;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean master) {
        isMaster = master;
    }

    public StopWatch getDuelTimer() {
        return duelTimer;
    }
}
