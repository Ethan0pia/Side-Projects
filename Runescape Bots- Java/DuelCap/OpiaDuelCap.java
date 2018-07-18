package com.ethan0pia.bots.DuelCap;

import com.ethan0pia.bots.DuelCap.branches.Root;
import com.ethan0pia.bots.DuelCap.ui.DuelingUI;
import com.ethan0pia.bots.DuelCap.uiLite.DuelingUILite;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.data.Access;
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

public class OpiaDuelCap extends TreeBot implements ChatboxListener, EmbeddableUI {

    private Area duelingLobby = new Area.Rectangular(new Coordinate(3331,3241,0), new Coordinate(3351,3223,0));
    private Area duelArea = new Area.Rectangular(new Coordinate(3361,3260,0 ), new Coordinate(3391,3242,0));
    private boolean isMaster;
    private boolean guiWait;
    private String opponentsName;
    private int duelsCompleted;
    private Player player;
    private Player opponent;
    private List<String> players;
    private boolean sendChallenge;
    private boolean paid = true;

    private DuelingUI infoUI;
    private DuelingUILite infoUILite;
    private SimpleObjectProperty<Node> botInterfaceProperty;
    private StopWatch stopWatch = new StopWatch(), duelWatch = new StopWatch();

    public OpiaDuelCap(){
        setEmbeddableUI(this);
        opponentsName="";
        guiWait=true;
        duelsCompleted=0;
        players = new ArrayList<>();
        sendChallenge=false;
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
            if(Environment.getBot().getMetaData().getHourlyPrice().doubleValue()>0 || Environment.getBot().getMetaData().getAccess().equals(Access.PRIVATE)) {
                botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new DuelingUI(this));
                paid=true;
            }else{
                botInterfaceProperty = new SimpleObjectProperty<>(infoUILite = new DuelingUILite(this));
                paid=false;
            }
        }
        return botInterfaceProperty;
    }

    @Override
    public void onMessageReceived(MessageEvent event) {
        if(event.getSender()!=null && event.getSender().equals(getOpponentsName())) {
                sendChallenge = true;
        }
    }

    @Override
    public void onStart(String... args){
        setLoopDelay(300, 800);
    }

    public StopWatch getDuelWatch() {
        return duelWatch;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public boolean isSendChallenge() {
        return sendChallenge;
    }

    public void setSendChallenge(boolean sendChallenge) {
        this.sendChallenge = sendChallenge;
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

    public DuelingUILite getInfoUILite(){return infoUILite;}

    public boolean isPaid() {
        return paid;
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
}
