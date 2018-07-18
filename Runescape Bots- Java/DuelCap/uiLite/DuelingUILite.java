package com.ethan0pia.bots.DuelCap.uiLite;

import com.ethan0pia.bots.DuelCap.OpiaDuelCap;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * This will show various live stats on the bot
 */
public class DuelingUILite extends GridPane implements Initializable {

    @FXML
    private Label uiRuntime, uiDuels, uiRate, masterMins, slaveMins;

    @FXML
    private Button start_BT, load;

    @FXML
    private ChoiceBox<String> players;

    @FXML
    private CheckBox master, slave;

    private OpiaDuelCap bot;

    private int slaveMinutes = 0;
    private int masterMinutes = 0;
    private String slaveMinutesToday;
    private String masterMinutesToday;

    public DuelingUILite(OpiaDuelCap bot) {
        this.bot = bot;
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/DuelCap/uiLite/DuelingUILite.fxml"));
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

        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
        String savedDate = bot.getSettings().getProperty("date");
        slaveMinutesToday = bot.getSettings().getProperty("slaveMinutes");
        masterMinutesToday = bot.getSettings().getProperty("masterMinutes");
        if(savedDate ==null || slaveMinutesToday == null || !savedDate.equals(date) || masterMinutesToday == null) {
            bot.getSettings().setProperty("date", date);
            bot.getSettings().setProperty("slaveMinutes", "0");
            bot.getSettings().setProperty("masterMinutes", "0");
            slaveMinutesToday = "0";
            masterMinutesToday = "0";
        }

        masterMins.textProperty().set(masterMinutesToday);
        slaveMins.textProperty().set(slaveMinutesToday);

        load.setOnAction(getLoadAction());
        start_BT.setOnAction(getStart_BTAction());
        master.setOnAction(getMasterAction());
        slave.setOnAction(getSlaveAction());
    }



    public void update(){
        if(bot.getStopWatch().isRunning()){
            int tempMins = (int) bot.getStopWatch().getRuntime(MINUTES);
            if(bot.isMaster()) {
                if (masterMinutes < tempMins) {
                    masterMinutes = tempMins;
                    if (masterMinutesToday != null) {
                        int totalMins = Integer.parseInt(masterMinutesToday);
                        totalMins++;
                        masterMinutesToday = String.valueOf(totalMins);
                        masterMins.textProperty().set(masterMinutesToday);
                        bot.getSettings().setProperty("masterMinutes", String.valueOf(totalMins));
                        if (totalMins >= 30) {
                            ClientUI.showAlert("You ran this bot for 30 minutes today. Please use the paid version for more time.");
                            bot.stop("Already ran this today.");
                        }
                    }
                }else if(Integer.parseInt(masterMinutesToday)>=30){
                    ClientUI.showAlert("You ran this bot for 30 minutes today. Please use the paid version for more time.");
                    bot.stop("Already ran this today.");
                }
            }else{
                if (slaveMinutes < tempMins) {
                    slaveMinutes = tempMins;
                    if (slaveMinutesToday != null) {
                        int totalMins = Integer.parseInt(slaveMinutesToday);
                        totalMins++;
                        slaveMinutesToday = String.valueOf(totalMins);
                        slaveMins.textProperty().set(slaveMinutesToday);
                        bot.getSettings().setProperty("slaveMinutes", String.valueOf(totalMins));
                        if (totalMins >= 30) {
                            ClientUI.showAlert("You ran this bot for 30 minutes today. Please use the paid version for more time.");
                            bot.stop("Already ran this today.");
                        }
                    }
                }else if(Integer.parseInt(slaveMinutesToday)>=30){
                    ClientUI.showAlert("You ran this bot for 30 minutes today. Please use the paid version for more time.");
                    bot.stop("Already ran this today.");
                }
            }

            uiRuntime.setText(bot.getStopWatch().getRuntimeAsString());
            uiDuels.setText(""+bot.getDuelsCompleted());
            uiRate.setText(""+(int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), bot.getDuelsCompleted())+"ph");
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
                List<String> list = bot.getPlayers();
                ObservableList<String> obList = FXCollections.observableList(list);
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
                    bot.setOpponentsName(players.getSelectionModel().getSelectedItem().trim());
                }
                else{
                    ClientUI.showAlert("You have to select an opponent!");
                    Environment.getBot().stop("no opponent");
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
                bot.stop("Stop Button Pressed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
