package com.ethan0pia.bots.WineGrabber;

import com.ethan0pia.bots.WineGrabber.branches.IsPlayerNull;
import com.ethan0pia.bots.WineGrabber.ui.WineInfoUI;
import com.ethan0pia.bots.WineGrabber.uiLite.WineInfoUILite;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.script.data.Access;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.listeners.events.ItemEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;

public class OpiaWineGrabber extends TreeBot implements InventoryListener, EmbeddableUI {

    private int winePrice = GrandExchange.lookup(245).getPrice(), cameraYaw = 400;
    private double cameraPitch = 1;
    private int wineCount, wineLost = 0, minutes = 0, bankPreset = 1, stopTime = 0, bankLocation = 0;
    private boolean run=false, bankBool=false, go = false, useTeleport = false, useCam = true;
    private String foodType;
    private Player player;
    private Coordinate telegrabSpot=new Coordinate(2952,3474,0);

    private WineInfoUI infoUI;
    private WineInfoUILite infoUILite;
    private SimpleObjectProperty<Node> botInterfaceProperty;

    private StopWatch stopWatch = new StopWatch();

    public OpiaWineGrabber(){
        wineCount = 0;
        updateInfo();
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask()
    {
        return new IsPlayerNull(this);
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            if(Environment.getBot().getMetaData().getHourlyPrice().doubleValue()>0 || Environment.getBot().getMetaData().getAccess().equals(Access.PRIVATE)) {
                botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new WineInfoUI(this));
            }else{
                botInterfaceProperty = new SimpleObjectProperty<>(infoUILite = new WineInfoUILite(this));
            }
        }
        return botInterfaceProperty;
    }

    @Override
    public void onStart(String... args){
        stopWatch.start();
        setLoopDelay(500, 1000);
        new LoopingThread(() -> Platform.runLater(this::updateInfo), 1000).start();
        getEventDispatcher().addListener(this);
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        ItemDefinition definition = event.getItem().getDefinition();
        if (definition != null && definition.getName().contains("Wine of Zamorak")) {
            wineCount++;
        }
    }

    // This method is used to update the GUI thread from the bot thread
    private void updateInfo() {
        try {
            // Assign all values to a new instance of the Info class
            int rate;
            int lossRate;
            if(wineLost>0){
                lossRate = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), wineLost);
            }else{
                lossRate=0;
            }
            if(wineCount>0) {
                rate = (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), wineCount);
            }else{
                rate=0;
            }
            String runTime = stopWatch.getRuntimeAsString();
            int tempMins = (int) stopWatch.getRuntime(MINUTES);

            if(stopTime!=0 && tempMins>=stopTime){
                ClientUI.showAlert("Stop time reached.");
                this.stop("Stop time reached.");
            }

            if(runTime != null) {
                if(infoUI!=null) {
                    infoUI.update(rate, wineCount, runTime, winePrice, wineLost, lossRate);
                }else if (infoUILite!=null){
                    int minutesAdd = 0;
                    if(tempMins==0){
                        minutesAdd = 3;
                    } else if(minutes<tempMins){
                        minutesAdd = 1;
                        minutes = tempMins;
                    }
                    infoUILite.update(rate, wineCount, runTime, winePrice, wineLost, lossRate, minutesAdd);
                }
            }
            // Assign all values to a new instance of the Info class
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int getBankPreset() {
        return bankPreset;
    }

    public void setBankPreset(int bankPreset) {
        this.bankPreset = bankPreset;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }

    public boolean isGo() {
        return go;
    }

    public void setGo(boolean go) {
        this.go = go;
    }

    public boolean isUseTeleport() {
        return useTeleport;
    }

    public void setUseTeleport(boolean useTeleport) {
        this.useTeleport = useTeleport;
    }

    public boolean isUseCam() {
        return useCam;
    }

    public void setUseCam(boolean useCam) {
        this.useCam = useCam;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isRun() {
        return run;
    }
    public void setRun(boolean run){
        this.run=run;
    }

    public Coordinate getTelegrabSpot() {
        return telegrabSpot;
    }

    public boolean isBankBool() {
        return bankBool;
    }
    public void setBankBool(boolean bankBool) {
        this.bankBool = bankBool;
    }

    public int getWineLost() {
        return wineLost;
    }
    public void setWineLost(int wineLost) {
        this.wineLost = wineLost;
    }

    public int getBankLocation() {
        return bankLocation;
    }
    public void setBankLocation(int bankLocation) {
        this.bankLocation = bankLocation;
    }

    public int getCameraYaw() {
        return cameraYaw;
    }

    public void setCameraYaw(int cameraYaw) {
        this.cameraYaw = cameraYaw;
    }

    public double getCameraPitch() {
        return cameraPitch;
    }

    public void setCameraPitch(double cameraPitch) {
        this.cameraPitch = cameraPitch;
    }
}
