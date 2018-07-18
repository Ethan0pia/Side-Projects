package com.ethan0pia.bots.WineGrabberUltra.ui;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class UltraWineInfoUI extends GridPane implements Initializable{

    private OpiaWineGrabberUltra bot;

    @FXML
    private Label moneyGained, uiTime, winePh, moneyPh, wineCollected, lost, lostRate, currentTask, alias, shiftDuration, uiShiftRuntime, magicLevel, magicLevelsGained;

    @FXML
    private TabPane tabs;

    @FXML
    private CheckBox useTeleport, returnToWorld, setupBankPreset;

    @FXML
    private ChoiceBox<String> shiftChoice, foodType;

    @FXML
    private ChoiceBox<Integer> trollHops, wineHops;

    @FXML
    private Button startButton, kill;

    @FXML
    private TextField secondAlias, pitch, yaw, killer, world;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shiftChoice.getItems().addAll("Disable Shifts", "1 hour", "2 hours","3 hours","4 hours","5 hours","6 hours","7 hours","8 hours", "9 hours","10 hours","11 hours","12 hours","13 hours","14 hours","15 hours","16 hours","17 hours","18 hours","19 hours","20 hours", "21 hours","22 hours","23 hours", "24 hours");
        foodType.getItems().addAll("Trout", "Salmon", "Pike", "Tuna", "Lobster");
        trollHops.getItems().addAll(0,1,2,3,4,5);
        wineHops.getItems().addAll(0,1,2,3,4,5);

        bot.getSettings();

        String setting = bot.getSettings().getProperty("shiftChoice");
        if(setting!=null){
            shiftChoice.getSelectionModel().select(Integer.parseInt(setting));
        }else{
            shiftChoice.getSelectionModel().select(24);
        }

        setting = bot.getSettings().getProperty("yaw");
        if(setting!=null){
            yaw.setText(setting);
        }else{
            yaw.setText(""+ 30);
        }

        setting = bot.getSettings().getProperty("pitch");
        if(setting!=null){
            pitch.setText(setting);
        }else{
            pitch.setText(""+ 0.8);
        }

        setting = bot.getSettings().getProperty("trollHops");
        if(setting!=null){
            trollHops.getSelectionModel().select(Integer.parseInt(setting));
        }else{
            trollHops.getSelectionModel().select(0);
        }

        setting = bot.getSettings().getProperty("wineHops");
        if(setting!=null){
            wineHops.getSelectionModel().select(Integer.parseInt(setting));
        }else{
            wineHops.getSelectionModel().select(0);
        }

        setting = bot.getSettings().getProperty("foodType");
        if(setting!=null){
            foodType.getSelectionModel().select(Integer.parseInt(setting));
        }else{
            foodType.getSelectionModel().select(0);
        }

        setting = bot.getSettings().getProperty("useTeleport");
        if(setting!=null){
            useTeleport.setSelected(Boolean.parseBoolean(setting));
        }



        setting = bot.getSettings().getProperty("setupBankPreset");
        if(setting!=null){
            setupBankPreset.setSelected(Boolean.parseBoolean(setting));
        }

        setting = bot.getSettings().getProperty("returnToWorld");
        if(setting!=null){
            returnToWorld.setSelected(Boolean.parseBoolean(setting));
        }

        startButton.setOnAction(startButton());
        kill.setOnAction(kill());

        setVisible(true);
    }

    private EventHandler<ActionEvent> kill(){
        return event -> {
            try {
                if(killer.getText()!=null && !killer.getText().equals("")) {
                    if(world.getText()!=null && !world.getText().equals("")){
                        bot.setKillWorld(Integer.parseInt(world.getText()));
                    }
                    bot.setKiller(killer.getText().trim());
                    bot.setKill(true);
                    kill.setText("Getting killed by "+ killer.getText());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> startButton(){
        return event -> {
            try {
                tabs.getSelectionModel().select(1);
                bot.setGo(true);
                bot.getSettings().setProperty("foodType", String.valueOf(foodType.getSelectionModel().getSelectedIndex()));
                bot.getSettings().setProperty("shiftChoice", String.valueOf(shiftChoice.getSelectionModel().getSelectedIndex()));
                bot.getSettings().setProperty("trollHops", String.valueOf(trollHops.getSelectionModel().getSelectedIndex()));
                bot.setMaxHopsTrolls(trollHops.getValue());
                bot.getSettings().setProperty("wineHops", String.valueOf(wineHops.getSelectionModel().getSelectedIndex()));
                bot.setMaxHopWine(wineHops.getValue());
                bot.setAlias(secondAlias.getText().trim());
                bot.setShiftLength(shiftChoice.getSelectionModel().getSelectedIndex());
                shiftDuration.textProperty().set(shiftChoice.getSelectionModel().getSelectedItem());
                bot.setFoodType(foodType.getSelectionModel().getSelectedItem().trim());
                bot.getSettings().setProperty("useTeleport", String.valueOf(useTeleport.isSelected()));
                bot.setUseTeleport(useTeleport.isSelected());
                bot.getSettings().setProperty("yaw", yaw.getText());
                int camYaw;
                if(yaw.getText()!=null){
                    camYaw =Integer.parseInt(yaw.getText());
                }else{
                    camYaw = 30;
                }
                bot.setCameraYaw(camYaw);
                bot.getSettings().setProperty("pitch", ""+camYaw);
                double camPitch;
                if(pitch.getText()!=null){
                    camPitch =Double.parseDouble(pitch.getText());
                }else{
                    camPitch = 0.8;
                }
                bot.setCameraPitch(camPitch);
                bot.getSettings().setProperty("pitch", ""+camPitch);
                bot.getSettings().setProperty("setupBankPreset", String.valueOf(setupBankPreset.isSelected()));
                bot.setDisableBankPreset(setupBankPreset.isSelected());
                bot.getSettings().setProperty("returnToWorld", String.valueOf(returnToWorld.isSelected()));
                bot.setReturnToWorld(returnToWorld.isSelected());
                startButton.setText("Update Settings");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

    }

    public UltraWineInfoUI(OpiaWineGrabberUltra bot) {
        this.bot = bot;
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/WineGrabberUltra/ui/UltraWineUI.fxml"));
        loader.setController(this);
        // NOTE: By setting the root to (this) you must change your .fxml to reflect fx:root
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // This method will update the text that is presented to the end user
    public void update( int rate, int wineCount, String runTime, String shiftRuntime, int value, int wineLost, int lossRate, int magicLvl, int magicLvlGained, String currTask, String aliass, boolean killing) {
        try {

            int money = wineCount*value;
            int gainRate = rate*value;
            wineCollected.textProperty().set("" + wineCount);
            winePh.textProperty().set(""+ rate);
            uiTime.textProperty().set("" + runTime);
            moneyGained.textProperty().set("" + money +"gp");
            moneyPh.textProperty().set("" + gainRate+"gp");
            lost.textProperty().set(""+wineLost);
            lostRate.textProperty().set(""+lossRate);
            magicLevelsGained.textProperty().set(""+magicLvlGained);
            uiShiftRuntime.textProperty().set(shiftRuntime);
            magicLevel.textProperty().set(""+ magicLvl);
            if(aliass!=null){
                alias.textProperty().set(aliass);
            }
            if(currTask!=null) {
                currentTask.textProperty().set(currTask);
            }
            if(!killing){
                kill.setText("Kill Bot");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
