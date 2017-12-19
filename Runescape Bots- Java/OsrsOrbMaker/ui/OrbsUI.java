package com.ethan0pia.bots.OsrsOrbMaker.ui;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class OrbsUI extends GridPane implements Initializable{

    @FXML
    private Label runtime, orbsMade, orbsPh, gp, gpPh;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVisible(true);
    }

    // An object property is a container of an object, which can be added
    // listeners to. In this case the property contains our controller class
    // (this)

    public OrbsUI(OsrsOrbMaker bot) {
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/OsrsOrbMaker/ui/OrbsUI.fxml"));
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
    public void update(String runTime, int rate, int orbsGained ,int gpPerHour, int gpGained) {
        try {
            orbsMade.textProperty().set(""+orbsGained);
            orbsPh.textProperty().set("" + rate);
            gp.textProperty().set("" + gpGained);
            gpPh.textProperty().set("" + gpPerHour);
            runtime.textProperty().set("" + runTime);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
