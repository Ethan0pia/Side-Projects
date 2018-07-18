package com.ethan0pia.bots.WineGrabber.ui;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
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


public class WineInfoUI extends GridPane implements Initializable{

    private OpiaWineGrabber bot;

    @FXML
    private Label moneyGained, uiTime, winePh, moneyPh, wineCollected, lost, lostRate;

    @FXML
    private CheckBox useTeleport, disableCam;

    @FXML
    private ChoiceBox<Integer> bankPreset;

    @FXML
    private ChoiceBox<String> foodType, bankChoice;

    @FXML
    private Button startButton;

    @FXML
    private TextField stopTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bankPreset.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        foodType.getItems().addAll("Trout", "Salmon", "Pike", "Tuna", "Lobster");
        bankChoice.getItems().addAll("Falador", "Taverley", "Edgeville");
        bot.getSettings();

        String setting = bot.getSettings().getProperty("bankPreset");
        if(setting!=null){
            bankPreset.getSelectionModel().select(Integer.parseInt(setting));
        }else{
            bankPreset.getSelectionModel().select(0);
        }

        setting = bot.getSettings().getProperty("bankChoice");
        if(setting!=null){
            bankChoice.getSelectionModel().select(Integer.parseInt(setting));
        }else{
            bankChoice.getSelectionModel().select(0);
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

        setting = bot.getSettings().getProperty("disableCam");
        if(setting!=null){
            disableCam.setSelected(Boolean.parseBoolean(setting));
        }

        startButton.setOnAction(startButton());
        setVisible(true);
    }

    private EventHandler<ActionEvent> startButton(){
        return event -> {
            try {
                bot.setGo(true);
                bot.getSettings().setProperty("bankPreset", String.valueOf(bankPreset.getSelectionModel().getSelectedIndex()));
                bot.getSettings().setProperty("foodType", String.valueOf(foodType.getSelectionModel().getSelectedIndex()));
                bot.getSettings().setProperty("bankChoice", String.valueOf(bankChoice.getSelectionModel().getSelectedIndex()));
                bot.setBankPreset(bankPreset.getSelectionModel().getSelectedIndex()+1);
                bot.setFoodType(foodType.getSelectionModel().getSelectedItem().trim());
                bot.getSettings().setProperty("useTeleport", String.valueOf(useTeleport.isSelected()));
                bot.setUseTeleport(useTeleport.isSelected());
                bot.getSettings().setProperty("disableCam", String.valueOf(disableCam.isSelected()));
                bot.setUseCam(!disableCam.isSelected());
                bot.setBankLocation(bankChoice.getSelectionModel().getSelectedIndex());
                if(!stopTime.getText().equals("")) {
                    try {
                        bot.setStopTime(Integer.parseInt(stopTime.getText()));
                    }catch(Exception e){
                        bot.setStopTime(0);
                    }
                }
                startButton.setText("Update Settings");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

    }

    public WineInfoUI(OpiaWineGrabber bot) {
        this.bot = bot;
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/WineGrabber/ui/WineUI.fxml"));
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
    public void update( int rate, int wineCount, String runTime, int value, int wineLost, int lossRate) {
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
