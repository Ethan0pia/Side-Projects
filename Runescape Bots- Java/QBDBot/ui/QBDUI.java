package com.ethan0pia.bots.QBDBot.ui;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
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
public class QBDUI extends GridPane implements Initializable {

    @FXML
    private Label runtime,kills,killsph;

    public QBDUI(OpiaQBD bot) {
        // Load the fxml file using RuneMate's resources class.
        FXMLLoader loader = new FXMLLoader();

        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/QBDBot/ui/QBDUI.fxml"));

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
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setVisible(true);
    }

/********************************************************************************************************************************************************
//-----------------------------------------------------------------updates--UI-----------------------------------------------------------------------/
 ******************************************************************************************************************************************************/

    public void update(String runTime,int numKills, int killsPh){

        if(runTime!=null){
            runtime.textProperty().set(runTime);
            kills.textProperty().set("" + numKills);
            killsph.textProperty().set("" + killsPh);
        }
    }
}