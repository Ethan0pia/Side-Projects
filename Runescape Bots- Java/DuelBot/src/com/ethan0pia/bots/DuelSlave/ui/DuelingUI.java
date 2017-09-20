package com.ethan0pia.bots.DuelSlave.ui;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * This will show various live stats on the bot
 */
public class DuelingUI extends GridPane implements Initializable {

    @FXML
    Label savedPlayer, uiRuntime, uiDuels, uiRate;

    @FXML
    Button start_BT, load;

    @FXML
    ChoiceBox players;

    @FXML
    CheckBox master, slave;

    private DuelingSlave bot;
    private List<String> nearbyPlayers;

    public DuelingUI(DuelingSlave bot) {
        this.bot = bot;
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/DuelSlave/ui/DuelingUI.fxml"));
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
        load.setOnAction(getLoadAction());
        start_BT.setOnAction(getStart_BTAction());
        master.setOnAction(getMasterAction());
        slave.setOnAction(getSlaveAction());
        if(bot.getSettings().getProperty("savedPlayer")!=null) {
            String savedPlayer = bot.getSettings().getProperty("savedPlayer").trim();
            this.savedPlayer.textProperty().set(savedPlayer);
        }
    }



    public void update(){
        if(bot.getStopWatch().isRunning()){
            uiRuntime.setText(bot.getStopWatch().getRuntimeAsString());
            uiDuels.setText(""+bot.getDuelsCompleted());
            uiRate.setText(""+(int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), bot.getDuelsCompleted())+"ph");
            if(bot.getPlayers()!=null){
                this.nearbyPlayers=bot.getPlayers();
            }
        }
    }

    private EventHandler<ActionEvent> getMasterAction() {
        return event -> {
            try {
                if (slave.isSelected()) {
                    slave.setSelected(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> getSlaveAction() {
        return event -> {
            try {
                if (master.isSelected()) {
                    master.setSelected(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> getLoadAction() {
        return event -> {
            try {
                this.savedPlayer.textProperty().set("");
                List<String> list = bot.getPlayers();
                ObservableList obList = FXCollections.observableList(list);
                if(players.getItems()!=null) {
                    players.getItems().clear();
                }
                players.setItems(obList);
                players.getSelectionModel().selectFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> getStart_BTAction() {
        return event -> {
            try {
                if(players.getSelectionModel().getSelectedItem()!=null) {
                    bot.getSettings().setProperty("savedPlayer", players.getSelectionModel().getSelectedItem().toString());
                    bot.setOpponentsName(players.getSelectionModel().getSelectedItem().toString().trim());
                }
                if (players.getSelectionModel().getSelectedItem()==null){
                    bot.setOpponentsName(savedPlayer.getText().trim());
                }
                if(master.isSelected()){
                    bot.setMaster(true);
                }else{
                    bot.setMaster(false);
                }
                start_BT.textProperty().set("Stop");
                start_BT.setOnAction(getStop_BTAction());
                bot.setGuiWait(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }

    private EventHandler<ActionEvent> getStop_BTAction() {
        return event -> {
            try {
                bot.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
