package com.ethan0pia.bots.WineGrabberUltra;

import com.ethan0pia.bots.WineGrabberUltra.ui.UltraWineInfoUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
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

import static java.util.concurrent.TimeUnit.HOURS;

public class OpiaWineGrabberUltra extends TreeBot implements InventoryListener, EmbeddableUI {

    private int winePrice = GrandExchange.lookup(245).getPrice(), cameraYaw = 30,currentMagicLevel=-1,magicLevelGained=0,shiftLength=8,
            maxHopsTrolls = 0, maxHopWine = 0, currHopsTrolls = 0, currHopWine = 0, originalWorld = 0, killWorld = 0;
    private double cameraPitch = 0.8;
    private int wineCount, wineLost = 0;
    private boolean run=false, bankBool=false, go = false, useTeleport = false, forceShiftEnd = false, checked = false,
            bankPreset = false, returnToWorld = false, disableBankPreset = false, kill = false;
    private String foodType, alias1, alias2, currentTask, killer;
    private Player player;
    private Coordinate telegrabSpot=new Coordinate(2952,3474,0);
    private ActionBar.Slot telegrab = null,rangedBasic = null,firstThreshold = null, magicBasic1 = null,magicBasic2 = null,
            magicBasic3 = null, magicBasic4 = null, teleportAbility = null, food = null;

    private UltraWineInfoUI infoUI;
    private SimpleObjectProperty<Node> botInterfaceProperty;

    private StopWatch stopWatch = new StopWatch();
    private StopWatch shiftWatch = new StopWatch();

    public OpiaWineGrabberUltra(){
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
            botInterfaceProperty = new SimpleObjectProperty<>(infoUI = new UltraWineInfoUI(this));
        }
        return botInterfaceProperty;
    }

    @Override
    public void onStart(String... args){
        stopWatch.start();
        shiftWatch.start();
        alias1 = Environment.getAccountAlias();
        setLoopDelay(500, 800);
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
            String shiftRuntime = shiftWatch.getRuntimeAsString();

            if(runTime != null && shiftRuntime!=null) {
                if(infoUI!=null) {
                    infoUI.update(rate, wineCount, runTime,shiftRuntime, winePrice, wineLost, lossRate, currentMagicLevel, magicLevelGained,currentTask,alias1, kill);
                }
            }
            // Assign all values to a new instance of the Info class
        }catch(Exception e){
            e.printStackTrace();
        }
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

    public void setCurrentMagicLevel(int currentMagicLevel) {
        if(this.currentMagicLevel != -1 && this.currentMagicLevel < currentMagicLevel){
            magicLevelGained++;
        }
        this.currentMagicLevel = currentMagicLevel;
    }

    public int getShiftLength() {
        return shiftLength;
    }

    public void setShiftLength(int shiftLength) {
        this.shiftLength = shiftLength;
    }

    public long getShiftWatch() {
        return shiftWatch.getRuntime(HOURS);
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public String currentAlias(){
        return alias1;
    }

    public boolean isAlias2(){
        return alias2 == null;
    }

    public void setAlias(String alias){
        alias2 = alias;
    }

    public void swapAndResetAlias(){
        String temp = alias1;
        alias1 = alias2;
        alias2 = temp;
        magicLevelGained = currentMagicLevel = currHopsTrolls = currHopWine = 0;
        shiftWatch.reset();
        telegrab = rangedBasic = firstThreshold =  magicBasic1 = magicBasic2 =  magicBasic3 = magicBasic4 = teleportAbility = food = null;
        checked = forceShiftEnd = bankPreset = false;
    }

    public boolean isForceShiftEnd() {
        return forceShiftEnd;
    }

    public void setForceShiftEnd(boolean forceShiftEnd) {
        this.forceShiftEnd = forceShiftEnd;
    }

    public ActionBar.Slot getTelegrab() {
        return telegrab;
    }

    public void setTelegrab(ActionBar.Slot telegrab) {
        this.telegrab = telegrab;
    }

    public ActionBar.Slot getRangedBasic() {
        return rangedBasic;
    }

    public void setRangedBasic(ActionBar.Slot rangedBasic) {
        this.rangedBasic = rangedBasic;
    }

    public ActionBar.Slot getFirstThreshold() {
        return firstThreshold;
    }

    public void setFirstThreshold(ActionBar.Slot firstThreshold) {
        this.firstThreshold = firstThreshold;
    }

    public ActionBar.Slot getMagicBasic1() {
        return magicBasic1;
    }

    public void setMagicBasic1(ActionBar.Slot magicBasic1) {
        this.magicBasic1 = magicBasic1;
    }

    public ActionBar.Slot getMagicBasic2() {
        return magicBasic2;
    }

    public void setMagicBasic2(ActionBar.Slot magicBasic2) {
        this.magicBasic2 = magicBasic2;
    }

    public ActionBar.Slot getMagicBasic3() {
        return magicBasic3;
    }

    public void setMagicBasic3(ActionBar.Slot magicBasic3) {
        this.magicBasic3 = magicBasic3;
    }

    public ActionBar.Slot getMagicBasic4() {
        return magicBasic4;
    }

    public void setMagicBasic4(ActionBar.Slot magicBasic4) {
        this.magicBasic4 = magicBasic4;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ActionBar.Slot getTeleportAbility() {
        return teleportAbility;
    }

    public void setTeleportAbility(ActionBar.Slot teleportAbility) {
        this.teleportAbility = teleportAbility;
    }

    public boolean isBankPreset() {
        return bankPreset;
    }

    public void setBankPreset(boolean bankPreset) {
        this.bankPreset = bankPreset;
    }

    public int getMaxHopsTrolls() {
        return maxHopsTrolls;
    }

    public void setMaxHopsTrolls(int maxHopsTrolls) {
        this.maxHopsTrolls = maxHopsTrolls;
    }

    public int getMaxHopWine() {
        return maxHopWine;
    }

    public void setMaxHopWine(int maxHopWine) {
        this.maxHopWine = maxHopWine;
    }

    public int getCurrHopsTrolls() {
        return currHopsTrolls;
    }

    public void setCurrHopsTrolls(int currHopsTrolls) {
        this.currHopsTrolls = currHopsTrolls;
    }

    public int getCurrHopWine() {
        return currHopWine;
    }

    public void setCurrHopWine(int currHopWine) {
        this.currHopWine = currHopWine;
    }

    public boolean isReturnToWorld() {
        return returnToWorld;
    }

    public void setReturnToWorld(boolean returnToWorld) {
        this.returnToWorld = returnToWorld;
    }

    public int getOriginalWorld() {
        return originalWorld;
    }

    public void setOriginalWorld(int originalWorld) {
        this.originalWorld = originalWorld;
    }

    public boolean isDisableBankPreset() {
        return disableBankPreset;
    }

    public void setDisableBankPreset(boolean disableBankPreset) {
        this.disableBankPreset = disableBankPreset;
    }

    public ActionBar.Slot getFood() {
        return food;
    }

    public void setFood(ActionBar.Slot food) {
        this.food = food;
    }

    public boolean isKill() {
        return kill;
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

    public String getKiller() {
        return killer;
    }

    public void setKiller(String killer) {
        this.killer = killer;
    }

    public int getKillWorld() {
        return killWorld;
    }

    public void setKillWorld(int killWorld) {
        this.killWorld = killWorld;
    }
}
