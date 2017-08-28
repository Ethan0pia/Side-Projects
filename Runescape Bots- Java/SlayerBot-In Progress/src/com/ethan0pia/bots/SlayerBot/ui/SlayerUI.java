package com.ethan0pia.bots.SlayerBot.ui;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * This will show various live stats on the bot
 */
public class SlayerUI extends GridPane implements Initializable {

    private GoodAssSlayerBot bot;

    @FXML
    Label uiRunetime, uiSlayerMaster, uiTasksCompleted, uiCurrentTask,uiTotalGP,uiGPH,uiAttackLevel,uiAttackXPGain,uiAttackLevelGain,
    uiSlayerLevel,uiSlayerXPGain,uiSlayerLevelGain,uiStrengthLevel,uiStrengthXPGain,uiStrengthLevelGain,uiDefenseLevel,uiDefenseXPGain,
    uiDefenseLevelGain,uiMagicLevel,uiMagicXPGain,uiMagicLevelGain,uiRangeLevel,uiRangeXPGain,uiRangeLevelGain,uiHPLevel,uiHPXPGain,
    uiHPLevelGain,uiPrayerLevel,uiPrayerXPGain,uiPrayerLevelGain, uiMonstersLeft;

    @FXML
    ProgressBar ConstitutionStatusBar, PrayerStatusBar, DefenseStatusBar, MagicStatusBar, RangedStatusBar, StrengthStatusBar, AttackStatusBar, SlayerStatusBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVisible(true);
    }

    // An object property is a container of an object, which can be added
    // listeners to. In this case the property contains our controller class
    // (this)

    private int tasksComplete, totalGP, gph, attLvl, attXPGain, attGain,slayLvl,slayXPGain,slayGain,strLvl,strXPGain,strGain,defLvl,defXPGain,
            defGain,magicLvl, magicXPGain,magicGain,rangeLvl,rangeXPGain,rangeGain,hpLvl,hpXPGain,hpGain,prayLvl,prayXPGain,prayGain, monstersLeft;
    private String runTime, currentTask;

    private double hpToLvl, slayToLvl, attToLvl, strToLvl, defToLvl, rangeToLvl, mageToLvl, prayToLvl;

    public SlayerUI(GoodAssSlayerBot bot, String master) {
        this.bot = bot;
        tasksComplete= totalGP= gph= attXPGain= attGain=slayXPGain=slayGain=strXPGain=strGain=defXPGain=
                defGain= magicXPGain=magicGain=rangeXPGain=rangeGain=hpXPGain=hpGain=prayXPGain=prayGain=monstersLeft=0;

        // Load the fxml file using RuneMate's resources class.
        FXMLLoader loader = new FXMLLoader();

        // Input your InfoUI FXML file location here.
        // NOTE: DO NOT FORGET TO ADD IT TO MANIFEST AS A RESOURCE
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/SlayerBot/ui/SlayerUI.fxml"));


        // Set this class as root AND Controller for the Java FX GUI
        loader.setController(this);

        // NOTE: By setting the root to (this) you must change your .fxml to reflect fx:root
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    public void update(int att, int slay, int def, int str, int mage, int range, int hp, int pray, int left){
       attGain =att;
       slayGain=slay;
       defGain=def;
       strGain=str;
       magicGain=mage;
       rangeGain=range;
       hpGain=hp;
       prayGain=pray;
       monstersLeft=left;
        
    }

    public void update1(int tasksComplete, int totalGP, int gph, int attLvl, int attXPGain, int slayLvl, int slayXPGain, int strLvl, int strXPGain, int defLvl, int defXPGain
            , int magicLvl, int magicXPGain, int rangeLvl, int rangeXPGain, int hpLvl, int hpXPGain, int prayLvl, int prayXPGain, String runTime, String currentTask,
                        double hpToLvl, double slayToLvl, double attToLvl, double strToLvl, double defToLvl, double rangeToLvl, double mageToLvl, double prayToLvl) {

        this.attLvl=attLvl;
        this.slayLvl=slayLvl;
        this.strLvl=strLvl;
        this.defLvl=defLvl;
        this.magicLvl=magicLvl;
        this.rangeLvl=rangeLvl;
        this.hpLvl=hpLvl;
        this.prayLvl=prayLvl;
        this.tasksComplete=tasksComplete;
        this.totalGP=totalGP;
        this.gph=gph;
        this.attXPGain=attXPGain;
        this.slayXPGain=slayXPGain;
        this.strXPGain=strXPGain;
        this.defXPGain=defXPGain;
        this.magicXPGain=magicXPGain;
        this.rangeXPGain=rangeXPGain;
        this.hpXPGain=hpXPGain;
        this.prayXPGain=prayXPGain;

        this.runTime = runTime;
        this.currentTask = currentTask;

        this.hpToLvl=hpToLvl;
        this.slayToLvl=slayToLvl;
        this.attToLvl =attToLvl;
        this.strToLvl=strToLvl;
        this.defToLvl=defToLvl;
        this.rangeToLvl=rangeToLvl;
        this.mageToLvl=mageToLvl;
        this.prayToLvl=prayToLvl;



    }


    // This method will update the text that is presented to the end user
    public void update2() {
        try {
            Info i = bot.info;

            uiSlayerMaster.textProperty().set(bot.master);
            uiTasksCompleted.textProperty().set("" + tasksComplete);
            uiRunetime.textProperty().set(runTime);
            uiCurrentTask.textProperty().set(currentTask);

            uiTotalGP.textProperty().set("" + totalGP);
            uiGPH.textProperty().set("" + gph);

            uiAttackLevel.textProperty().set("" + attLvl);
            uiAttackXPGain.textProperty().set("" + attXPGain);
            uiAttackLevelGain.textProperty().set("" + attGain);

            uiSlayerLevel.textProperty().set("" + slayLvl);
            uiSlayerXPGain.textProperty().set("" +  slayXPGain);
            uiSlayerLevelGain.textProperty().set("" +  slayGain);

            uiStrengthLevel.textProperty().set("" +  strLvl);
            uiStrengthXPGain.textProperty().set("" +  strXPGain);
            uiStrengthLevelGain.textProperty().set("" +  strGain);

            uiDefenseLevel.textProperty().set("" +  defLvl);
            uiDefenseXPGain.textProperty().set("" +  defXPGain);
            uiDefenseLevelGain.textProperty().set("" +  defGain);

            uiMagicLevel.textProperty().set("" +  magicLvl);
            uiMagicXPGain.textProperty().set("" +  magicXPGain);
            uiMagicLevelGain.textProperty().set("" +  magicGain);

            uiRangeLevel.textProperty().set("" +  rangeLvl);
            uiRangeXPGain.textProperty().set("" +  rangeXPGain);
            uiRangeLevelGain.textProperty().set("" +  rangeGain);

            uiHPLevel.textProperty().set("" +  hpLvl);
            uiHPXPGain.textProperty().set("" +  hpXPGain);
            uiHPLevelGain.textProperty().set("" +  hpGain);

            uiPrayerLevel.textProperty().set("" +  prayLvl);
            uiPrayerXPGain.textProperty().set("" +  prayXPGain);
            uiPrayerLevelGain.textProperty().set("" +  prayGain);
            uiMonstersLeft.textProperty().set("" +  monstersLeft);


            ConstitutionStatusBar.setProgress(hpToLvl);
            SlayerStatusBar.setProgress(slayToLvl);
            AttackStatusBar.setProgress(attToLvl);
            StrengthStatusBar.setProgress(strToLvl);
            DefenseStatusBar.setProgress(defToLvl);
            MagicStatusBar.setProgress(mageToLvl);
            RangedStatusBar.setProgress(rangeToLvl);
            PrayerStatusBar.setProgress(prayToLvl);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
