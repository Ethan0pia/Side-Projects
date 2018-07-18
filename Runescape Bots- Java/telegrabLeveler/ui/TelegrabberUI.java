package com.ethan0pia.bots.TelegrabLeveler.ui;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
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


public class TelegrabberUI extends GridPane implements Initializable{

    @FXML
    private Label runtime, lvlGained, magicLvl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVisible(true);
    }

    // An object property is a container of an object, which can be added
    // listeners to. In this case the property contains our controller class
    // (this)

    public TelegrabberUI(TelegrabLeveler bot) {
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/TelegrabLeveler/ui/TeleUI.fxml"));
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
    public void update(String runTime, int magicGained ,int magicLevel) {
        try {
            magicLvl.textProperty().set(""+magicLevel);
            lvlGained.textProperty().set("" + magicGained);
            runtime.textProperty().set("" + runTime);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
