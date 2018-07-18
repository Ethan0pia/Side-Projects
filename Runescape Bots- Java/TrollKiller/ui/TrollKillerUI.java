package com.ethan0pia.bots.TrollKiller.ui;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class TrollKillerUI extends GridPane implements Initializable{

    private TrollKiller bot;

    @FXML
    private Label runtime, attack, magic, ranged;

    @FXML
    private TextField attackTarget, rangedTarget, magicTarget;

    @FXML
    private Button startButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bot.getSettings();

        String setting = bot.getSettings().getProperty("attackTarget");
        if(setting!=null){
            attackTarget.setText(setting);
        }else{
            attackTarget.setText(""+30);
        }

        setting = bot.getSettings().getProperty("rangedTarget");
        if(setting!=null){
            rangedTarget.setText(setting);
        }else{
            rangedTarget.setText(""+30);
        }

        setting = bot.getSettings().getProperty("magicTarget");
        if(setting!=null){
            magicTarget.setText(setting);
        }else{
            magicTarget.setText(""+30);
        }

        startButton.setOnAction(startButton());
        setVisible(true);
    }

    private EventHandler<ActionEvent> startButton(){
        return event -> {
            try {
                bot.setGo(true);
                bot.getSettings().setProperty("attackTarget", attackTarget.getText());
                bot.getSettings().setProperty("rangedTarget", rangedTarget.getText());
                bot.getSettings().setProperty("magicTarget", magicTarget.getText());
                bot.setRangedTarget(Integer.parseInt(rangedTarget.getText()));
                bot.setMagicTarget(Integer.parseInt(magicTarget.getText()));
                bot.setAttackTarget(Integer.parseInt(attackTarget.getText()));
                attackTarget.setDisable(true);
                magicTarget.setDisable(true);
                rangedTarget.setDisable(true);
                startButton.setDisable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

    }

    // An object property is a container of an object, which can be added
    // listeners to. In this case the property contains our controller class
    // (this)

    public TrollKillerUI(TrollKiller bot) {
        this.bot = bot;
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/TrollKiller/ui/TrollUI.fxml"));
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
    public void update(String runTime, int magicLvl, int rangedLvl, int attackLvl) {
        try {
            runtime.textProperty().set("" + runTime);
            attack.textProperty().set(""+attackLvl);
            ranged.textProperty().set(""+rangedLvl);
            magic.textProperty().set(""+magicLvl);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
