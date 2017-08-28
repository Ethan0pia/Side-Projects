package com.ethan0pia.bots.SlayerBot.ui;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.location.Coordinate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Java FX Controller for the FlaxFXGui class
 *
 * The controller class is where the logic and implementation of GUI events go.
 *      Ex. If you press on a Start button in a typical program, you'd expect the program to actually start.
 *          That event handling would be found here.
 *          NOTE:   You can assign a single class to be the FXML Loader And Controller.
 *                  To do this, just set your FXML's loader to .setController(this) in appropriate class.
 */
public class SlayerFXController implements Initializable {

    private GoodAssSlayerBot bot;

    @FXML
    ComboBox MeleeBar, RangedBar, MagicBar, Food, MeleePreset, RangedPreset, MagicPreset, SlayerMaster;

    @FXML
    Button Start_BT;


    public SlayerFXController(GoodAssSlayerBot bot) {
        this.bot = bot;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add Locations to the combo box
        SlayerMaster.getItems().addAll("Turael", "Spria");
        Food.getItems().addAll("Traut", "Salmon", "Lobster");
        MeleeBar.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        RangedBar.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        MagicBar.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        MeleePreset.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        RangedPreset.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        MagicPreset.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // If the Start Button is pressed, handle that even in the getStart_BTAction method
        Start_BT.setOnAction(getStart_BTAction());

        // Set the event for Location_ComboBox
        SlayerMaster.setOnAction(getLocation_ComboBoxEvent());
    }

    public EventHandler<ActionEvent> getStart_BTAction() {
        return event -> {
            try {
                // Initialize all variables in your bot
                bot.guiWait = false;
                bot.food = Food.getSelectionModel().getSelectedItem().toString();
                switch(SlayerMaster.getSelectionModel().getSelectedItem().toString()){
                    case "Turael":
                        bot.slayerMasterCoords = new Coordinate(2911,3422,0);
                        bot.master = "Turael";
                        break;
                    case "Spria":
                        bot.slayerMasterCoords = new Coordinate(2911,3422,0);
                        bot.master = "Spria";
                        break;
                }

                // Set the EmbeddableUI property to reflect your Info GUI
                Platform.runLater(() -> bot.setToInfoProperty());

            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }

    public EventHandler<ActionEvent> getLocation_ComboBoxEvent(){
        return event ->{
            // If a value is assigned to the Combo Box, enable the Start Button.
            if(SlayerMaster.getSelectionModel().getSelectedItem() != null)
                Start_BT.setDisable(false);
            else
                Start_BT.setDisable(true);
        };
    }
}
