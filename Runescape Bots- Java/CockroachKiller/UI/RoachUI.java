package com.ethan0pia.bots.CockroachKiller.UI;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class RoachUI extends GridPane implements Initializable{

    private Roach bot;

    @FXML
    private Label runtime, attack, magic, ranged;

    @FXML
    private TextField target;

    @FXML
    private ChoiceBox<String> skill;

    @FXML
    private Button startButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bot.getSettings();

        skill.getItems().addAll("Attack", "Magic", "Ranged");

        String setting = bot.getSettings().getProperty("target");
        if(setting!=null){
            target.setText(setting);
        }else{
            target.setText(""+50);
        }

        setting = bot.getSettings().getProperty("skill");
        if(setting!=null){
            skill.getSelectionModel().select(setting);
        }

        startButton.setOnAction(startButton());
        setVisible(true);
    }

    private EventHandler<ActionEvent> startButton(){
        return event -> {
            try {
                bot.setGo(true);
                bot.getSettings().setProperty("target", target.getText());
                bot.getSettings().setProperty("skill", skill.getSelectionModel().toString());
                bot.setTarget(Integer.parseInt(target.getText()));
                bot.setSkill(skill.getSelectionModel().getSelectedIndex());
                target.setDisable(true);
                skill.setDisable(true);
                startButton.setDisable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

    }

    // An object property is a container of an object, which can be added
    // listeners to. In this case the property contains our controller class
    // (this)

    public RoachUI(Roach bot) {
        this.bot = bot;
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/CockroachKiller/UI/Roach.fxml"));
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
