package com.ethan0pia.bots.GargoyleBot.ui;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * This will show various live stats on the bot
 */
public class GargUI extends GridPane implements Initializable {

    @FXML
    private Label uiTime,uiMoneyGained,uiGpPH;

    @FXML
    private Button start_BT;

    @FXML
    private ChoiceBox<String> food;

    @FXML
    private ChoiceBox<Boolean>usingMagic;

    private int totalGP, gph;

    private GargSlayer bot;

    public GargUI(GargSlayer bot) {
        // Load the fxml file using RuneMate's resources class.
        FXMLLoader loader = new FXMLLoader();
        this.bot = bot;

        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/GargoyleBot/ui/GargUI.fxml"));

        // Set FlaxFXController as the class that will be handling our events
        loader.setController(this);

        // Set the FXML load's root to this class
        // NOTE: By setting the root to (this) you must change your .fxml to reflect fx:root
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            Environment.getLogger().info("Error loading GUI");
            e.printStackTrace();
        }

        totalGP=gph=0;

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        setVisible(true);
        food.getItems().addAll("Traut", "Salmon", "Lobster", "Monkfish", "Shark");
        setDefaults(food);
        usingMagic.getItems().addAll(true, false);
        setDefaults(usingMagic);
        start_BT.setOnAction(getStart_BTAction());
    }

    private void setDefaults(ChoiceBox box){
        box.getSelectionModel().selectFirst();
    }

    private EventHandler<ActionEvent> getStart_BTAction() {
        return event -> {
            try {
                bot.getStopWatch().start();
                bot.setFoodType(food.getSelectionModel().getSelectedItem().trim());
                bot.setUsingMagic(usingMagic.getSelectionModel().getSelectedItem());
                bot.setGuiWait(false);
                start_BT.cancelButtonProperty();
                start_BT.textProperty().set("Smashing");
            }catch(Exception e){
                e.printStackTrace();
            }
        };
    }


/********************************************************************************************************************************************************
//-----------------------------------------------------------------updates--UI-----------------------------------------------------------------------/
 ******************************************************************************************************************************************************/

    public void update(String runTime,int totalGP, int gph){

        if(runTime!=null){
            uiTime.textProperty().set(runTime);
            uiMoneyGained.textProperty().set("" + totalGP);
            uiGpPH.textProperty().set("" + gph);
        }
    }
}