package com.ethan0pia.bots.SpiritualMages.ui;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * This will show various live stats on the bot
 */
public class SpiritualMagesUI extends GridPane implements Initializable {

    @FXML
    private TabPane tabs;

    @FXML
    private Label attXp, attXpPh, defXp, defXpPh, strXp, strXpPh, mageXp, mageXpPh, rangeXp, rangeXpPh, prayXp, prayXpPh, runtime,
        totalGp, gpPh, xpGained, xpPh, currentTask, conXp, conXpPh;

    @FXML
    private Button start, randomize;

    @FXML
    private TextField b1s, b2s, b3s, b4s, b5s, b6s, b7s, b8s, b1d, b2d, b3d, b4d, b5d, b6d, b7d, b8d, stopTime;

    @FXML
    private ChoiceBox<Integer> bankPreset, cBar;

    @FXML
    private CheckBox worldHop;

    @FXML
    private ChoiceBox<String> teleport;

    private int totalGP, gph, attXPGain,strXPGain,defXPGain,
            magicXPGain,rangeXPGain,hpXPGain,prayXPGain, timeStop;

    private TextField[] startTimes, durations;

    private long runtimeMin;

    private String currentTsk;

    private OpiaSpiritualMages bot;

    public SpiritualMagesUI(OpiaSpiritualMages bot) {
        // Load the fxml file using RuneMate's resources class.
        FXMLLoader loader = new FXMLLoader();
        this.bot = bot;

        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/SpiritualMages/ui/SpiritualMagesUII.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            Environment.getLogger().info("Error loading GUI");
            e.printStackTrace();
        }

        totalGP = gph = attXPGain = strXPGain = defXPGain = magicXPGain = rangeXPGain = hpXPGain = prayXPGain = timeStop = 0;
        runtimeMin = 0;

        startTimes = new TextField[]{b1s, b2s, b3s, b4s, b5s, b6s, b7s, b8s};
        durations = new TextField[]{b1d, b2d, b3d, b4d, b5d, b6d, b7d, b8d};
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setVisible(true);
        bankPreset.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        cBar.getItems().addAll(1,2,3,4,5,6);
        teleport.getItems().addAll("Camelot teleport", "Varrock teleport", "Lumbridge teleport", "Falador teleport", "Ardougne teleport", "Teleport to house", "Ectophial");
        bot.getSettings();
        String setting = bot.getSettings().getProperty("bankPreset");
        if(setting!=null){
            bankPreset.getSelectionModel().select(Integer.parseInt(setting));
        }else{
            bankPreset.getSelectionModel().select(0);
        }

        setting = bot.getSettings().getProperty("combatBar");
        if(setting!=null){
            cBar.getSelectionModel().select(Integer.parseInt(setting));
        }else{
            cBar.getSelectionModel().select(0);
        }

        setting = bot.getSettings().getProperty("worldHop");
        if(setting!=null){
            worldHop.setSelected(Boolean.parseBoolean(setting));
        }

        setting = bot.getSettings().getProperty("teleport");
        if(setting!=null){
            teleport.getSelectionModel().select(Integer.parseInt(setting));
        }else{
            teleport.getSelectionModel().select(0);
        }

        start.setOnAction(startButton());
        randomize.setOnAction(randomize());


    }

    private EventHandler<ActionEvent> randomize(){
        return event -> {
            try {
                int current = 0;
                int random = Random.nextInt(current+20,current+100);
                int duration = Random.nextInt(1,18);
                current = random+duration;
                for(int i = 0; i<8;i++){
                    startTimes[i].setText(""+random);
                    durations[i].setText(""+ duration);
                    random = Random.nextInt(current+40,current+120);
                    duration = Random.nextInt(5,20);
                    current = random+duration;
                }
                randomize.setText("Done Randomizing");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> startButton(){
        return event -> {
            try {
                tabs.getSelectionModel().select(1);
                bot.setGo(true);
                bot.getSettings().setProperty("bankPreset", String.valueOf(bankPreset.getSelectionModel().getSelectedIndex()));
                bot.setBankPreset(bankPreset.getSelectionModel().getSelectedIndex()+1);
                bot.getSettings().setProperty("combatBar", String.valueOf(cBar.getSelectionModel().getSelectedIndex()));
                bot.setCombatBar(cBar.getSelectionModel().getSelectedIndex()+1);
                bot.getSettings().setProperty("teleport", String.valueOf(teleport.getSelectionModel().getSelectedIndex()));
                bot.setTeleport(teleport.getSelectionModel().getSelectedItem());
                bot.getSettings().setProperty("worldHop", String.valueOf(worldHop.isSelected()));
                bot.setWorldHop(worldHop.isSelected());
                if (stopTime.getText() != null) {
                    timeStop = Integer.parseInt(stopTime.getText());
                }
                start.setDisable(true);
                randomize.setDisable(true);
                worldHop.setDisable(true);
                bankPreset.setDisable(true);
                cBar.setDisable(true);
                teleport.setDisable(true);
                stopTime.setDisable(true);
                for(TextField i : startTimes){
                    i.setDisable(true);
                }
                for(TextField i : durations){
                    i.setDisable(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

    }

    public int getBreakStart(int breakNum){
        if(startTimes[breakNum].getText()!=null && !startTimes[breakNum].getText().equals("")) {
            return Integer.parseInt(startTimes[breakNum].getText());
        }else{
            return 0;
        }
    }

    public int getBreakDuration(int breakNum){
        if(durations[breakNum].getText()!=null && !durations[breakNum].getText().equals("")) {
            return Integer.parseInt(durations[breakNum].getText());
        }else{
            return 0;
        }
    }

/********************************************************************************************************************************************************
//-----------------------------------------------------------------updates--UI-----------------------------------------------------------------------/
 ******************************************************************************************************************************************************/

    public void update1(int totalGP, int gph, int attXPGain, int strXPGain, int defXPGain
            , int magicXPGain, int rangeXPGain, int hpXPGain, int prayXPGain, String currentTask, long runtimeMin) {

        this.totalGP=totalGP;
        this.gph=gph;
        this.attXPGain=attXPGain;
        this.strXPGain=strXPGain;
        this.defXPGain=defXPGain;
        this.magicXPGain=magicXPGain;
        this.rangeXPGain=rangeXPGain;
        this.hpXPGain=hpXPGain;
        this.prayXPGain=prayXPGain;
        this.runtimeMin = runtimeMin;

        if(timeStop <= runtimeMin && timeStop > 0){
            bot.setStopBot(true);
        }
        this.currentTsk = currentTask;
        update2();
    }

    // This method will update the text that is presented to the end user
    private void update2() {
        try {
            currentTask.textProperty().set(currentTsk);

            totalGp.textProperty().set("" + totalGP);
            gpPh.textProperty().set("" + gph);

            attXp.textProperty().set("" + attXPGain);
            attXpPh.textProperty().set("" + (int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), attXPGain));

            strXp.textProperty().set("" +  strXPGain);
            strXpPh.textProperty().set("" +  (int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), strXPGain));

            defXp.textProperty().set("" +  defXPGain);
            defXpPh.textProperty().set("" +  (int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), defXPGain));

            mageXp.textProperty().set("" +  magicXPGain);
            mageXpPh.textProperty().set("" +  (int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), magicXPGain));

            rangeXp.textProperty().set("" +  rangeXPGain);
            rangeXpPh.textProperty().set("" +  (int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), rangeXPGain));

            conXp.textProperty().set("" +  hpXPGain);
            conXpPh.textProperty().set("" +  (int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), hpXPGain));

            prayXp.textProperty().set("" +  prayXPGain);
            prayXpPh.textProperty().set("" +  (int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), prayXPGain));

            int xp = attXPGain + strXPGain + defXPGain + magicXPGain + rangeXPGain + hpXPGain + prayXPGain;
            xpGained.textProperty().set(""+xp);
            xpPh.textProperty().set(""+(int) CommonMath.rate(TimeUnit.HOURS, bot.getStopWatch().getRuntime(), xp));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update3(String runTime){

        if(runtime!=null){
            runtime.textProperty().set(runTime);
        }
    }
}