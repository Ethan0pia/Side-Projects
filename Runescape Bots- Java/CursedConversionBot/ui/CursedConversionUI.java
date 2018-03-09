package com.ethan0pia.bots.CursedConversionBot.ui;

import com.ethan0pia.bots.CursedConversionBot.CursedConversionBot;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class CursedConversionUI extends GridPane implements Initializable{

    @FXML
    private Label energyConverted,energyPH,runtime;

    @FXML
    private ChoiceBox<String> energyType;

    @FXML
    private Button startButton;

    private CursedConversionBot bot;

    public CursedConversionUI(CursedConversionBot bot) {
        this.bot = bot;
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/CursedConversionBot/ui/CursedConversionUI.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            System.err.println("Error loading GUI");
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setVisible(true);
        bot.getSettings();
        startButton.setOnAction(getStartButtonAction());
        energyType.getItems().addAll( "Pale energy","Flickering energy","Bright energy","Glowing energy","Sparkling energy",
                "Gleaming energy","Vibrant energy","lustrous energy","Elder energy","Brilliant energy","Radiant energy",
                "Luminous energy","Incandescent energy");
        if(bot.getSettings().getProperty("savedEnergy")!=null) {
            String savedEnergy = bot.getSettings().getProperty("savedEnergy").trim();
            energyType.setValue(savedEnergy);
        }else{
            energyType.getSelectionModel().select(0);
        }
    }



    public void update(int rate, int itemCount, String runTime){
        runtime.setText(runTime);
        energyConverted.setText(""+itemCount);
        energyPH.setText(""+rate+"ph");
    }

    private EventHandler<ActionEvent> getStartButtonAction() {
        return event -> {
            try {
                if(energyType.getSelectionModel().getSelectedItem()!=null) {
                    bot.getSettings().setProperty("savedType", energyType.getSelectionModel().getSelectedItem().trim());
                    bot.setEnergy(energyType.getSelectionModel().getSelectedItem().trim());
                }
                startButton.textProperty().set("Update");
                bot.setGuiWait(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }
}
