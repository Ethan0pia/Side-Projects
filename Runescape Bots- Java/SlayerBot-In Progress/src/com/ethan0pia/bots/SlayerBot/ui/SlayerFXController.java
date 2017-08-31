package com.ethan0pia.bots.SlayerBot.ui;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.io.ManagedProperties;
import com.runemate.game.api.script.framework.AbstractBot;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.w3c.dom.ranges.Range;

import java.io.File;
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
    ComboBox MeleeBar, RangedBar, MagicBar, Food, MeleePreset, RangedPreset, MagicPreset, SlayerMaster, MinVal;

    @FXML
    Button Start_BT;


    public SlayerFXController(GoodAssSlayerBot bot) {
        this.bot = bot;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add Locations to the combo box

        SlayerMaster.getItems().addAll("Turael", "Spria");
        if(bot.getSettings().getProperty("SlayerMaster")!=null){
            SlayerMaster.getSelectionModel().select(Integer.parseInt(bot.getSettings().getProperty("SlayerMaster")));
        }else{
            SlayerMaster.getSelectionModel().selectFirst();
        }
        Food.getItems().addAll("Traut", "Salmon", "Lobster");
        if(bot.getSettings().getProperty("Food")!=null){
            Food.getSelectionModel().select(Integer.parseInt(bot.getSettings().getProperty("Food")));
        }else{
            Food.getSelectionModel().selectFirst();
        }
        MeleeBar.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        if(bot.getSettings().getProperty("MeleeBar")!=null){
            MeleeBar.getSelectionModel().select(Integer.parseInt(bot.getSettings().getProperty("MeleeBar")));
        }else{
            MeleeBar.getSelectionModel().selectFirst();
        }
        RangedBar.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        if(bot.getSettings().getProperty("RangedBar")!=null){
            RangedBar.getSelectionModel().select(Integer.parseInt(bot.getSettings().getProperty("RangedBar")));
        }else{
            RangedBar.getSelectionModel().selectFirst();
        }
        MagicBar.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        if(bot.getSettings().getProperty("MagicBar")!=null){
            MagicBar.getSelectionModel().select(Integer.parseInt(bot.getSettings().getProperty("MagicBar")));
        }else{
            MagicBar.getSelectionModel().selectFirst();
        }
        MeleePreset.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        if(bot.getSettings().getProperty("MeleePreset")!=null){
            MeleePreset.getSelectionModel().select(Integer.parseInt(bot.getSettings().getProperty("MeleePreset")));
        }else{
            MeleePreset.getSelectionModel().selectFirst();
        }
        RangedPreset.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        if(bot.getSettings().getProperty("RangedPreset")!=null){
            RangedPreset.getSelectionModel().select(Integer.parseInt(bot.getSettings().getProperty("RangedPreset")));
        }else{
            RangedPreset.getSelectionModel().selectFirst();
        }
        MagicPreset.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        if(bot.getSettings().getProperty("MagicPreset")!=null){
            MagicPreset.getSelectionModel().select(Integer.parseInt(bot.getSettings().getProperty("MagicPreset")));
        }else{
            MagicPreset.getSelectionModel().selectFirst();
        }
        MinVal.getItems().addAll("0", "250","500","1000","2500","5000","7500","10000","20000","30000","40000","50000","100000","1000000");
        if(bot.getSettings().getProperty("MinVal")!=null){
            MinVal.getSelectionModel().select(Integer.parseInt(bot.getSettings().getProperty("MinVal")));
        }else{
            MinVal.getSelectionModel().selectFirst();
        }
        // If the Start Button is pressed, handle that even in the getStart_BTAction method
        Start_BT.setOnAction(getStart_BTAction());

        // Set the event for Location_ComboBox
        SlayerMaster.setOnAction(getLocation_ComboBoxEvent());
    }

    private EventHandler<ActionEvent> getStart_BTAction() {
        return event -> {
            try {
                // Initialize all variables in your bot
                bot.guiWait = false;
                switch(Food.getSelectionModel().getSelectedItem().toString()){
                    case "Salmon": bot.food="Salmon";
                                    break;
                    case "Lobster": bot.food="Lobster";
                }

                bot.meleeBar = Integer.parseInt(MeleeBar.getSelectionModel().getSelectedItem().toString());
                bot.rangedBar = Integer.parseInt(RangedBar.getSelectionModel().getSelectedItem().toString());
                bot.magicBar = Integer.parseInt(MagicBar.getSelectionModel().getSelectedItem().toString());
                bot.meleePreset = Integer.parseInt(MeleePreset.getSelectionModel().getSelectedItem().toString());
                bot.rangedPreset = Integer.parseInt(RangedPreset.getSelectionModel().getSelectedItem().toString());
                bot.magicPreset = Integer.parseInt(MagicPreset.getSelectionModel().getSelectedItem().toString());
                bot.minVal = Integer.parseInt(MinVal.getSelectionModel().getSelectedItem().toString());
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
                if(bot.getSettings()!=null) {
                    bot.getSettings().setProperty("SlayerMaster", Integer.toString(SlayerMaster.getSelectionModel().getSelectedIndex()));
                    bot.getSettings().setProperty("Food", Integer.toString(Food.getSelectionModel().getSelectedIndex()));
                    bot.getSettings().setProperty("MeleeBar", Integer.toString(MeleeBar.getSelectionModel().getSelectedIndex()));
                    bot.getSettings().setProperty("RangedBar", Integer.toString(RangedBar.getSelectionModel().getSelectedIndex()));
                    bot.getSettings().setProperty("MagicBar", Integer.toString(MagicBar.getSelectionModel().getSelectedIndex()));
                    bot.getSettings().setProperty("MeleePreset", Integer.toString(MeleePreset.getSelectionModel().getSelectedIndex()));
                    bot.getSettings().setProperty("RangedPreset", Integer.toString(RangedPreset.getSelectionModel().getSelectedIndex()));
                    bot.getSettings().setProperty("MagicPreset", Integer.toString(MagicPreset.getSelectionModel().getSelectedIndex()));
                    bot.getSettings().setProperty("MinVal", Integer.toString(MinVal.getSelectionModel().getSelectedIndex()));
                }


                // Set the EmbeddableUI property to reflect your Info GUI
                Platform.runLater(() -> bot.setToInfoProperty());

            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }

    private EventHandler<ActionEvent> getLocation_ComboBoxEvent(){
        return event ->{
            // If a value is assigned to the Combo Box, enable the Start Button.
            if(SlayerMaster.getSelectionModel().getSelectedItem() != null && Food.getSelectionModel().getSelectedItem() != null
                    && MeleeBar.getSelectionModel().getSelectedItem() != null && RangedBar.getSelectionModel().getSelectedItem() != null
                    && MagicBar.getSelectionModel().getSelectedItem() != null && MeleePreset.getSelectionModel().getSelectedItem() != null
                    && RangedPreset.getSelectionModel().getSelectedItem() != null && MagicPreset.getSelectionModel().getSelectedItem() != null
                    && MinVal.getSelectionModel().getSelectedItem() != null)
                Start_BT.setDisable(false);
            else
                Start_BT.setDisable(true);
        };
    }
}
