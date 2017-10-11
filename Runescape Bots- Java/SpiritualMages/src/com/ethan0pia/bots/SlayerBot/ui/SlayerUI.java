package com.ethan0pia.bots.SlayerBot.ui;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.util.Resources;
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

/**
 * This will show various live stats on the bot
 */
public class SlayerUI extends GridPane implements Initializable {

    @FXML
    private Label uiRunetime, uiCurrentTask,uiTotalGP,uiGPH,uiAttackLevel,uiAttackXPGain,uiAttackLevelGain,
            uiSlayerLevel,uiSlayerXPGain,uiSlayerLevelGain,uiStrengthLevel,uiStrengthXPGain,uiStrengthLevelGain,uiDefenseLevel,uiDefenseXPGain,
            uiDefenseLevelGain,uiMagicLevel,uiMagicXPGain,uiMagicLevelGain,uiRangeLevel,uiRangeXPGain,uiRangeLevelGain,uiHPLevel,uiHPXPGain,
            uiHPLevelGain,uiPrayerLevel,uiPrayerXPGain,uiPrayerLevelGain;

    @FXML
    private ProgressBar ConstitutionStatusBar, PrayerStatusBar, DefenseStatusBar, MagicStatusBar, RangedStatusBar, StrengthStatusBar, AttackStatusBar, SlayerStatusBar;

    @FXML
    private Button start_BT;

    private int totalGP, gph, attLvl, attXPGain, attGain,slayLvl,slayXPGain,slayGain,strLvl,strXPGain,strGain,defLvl,defXPGain,
            defGain,magicLvl, magicXPGain,magicGain,rangeLvl,rangeXPGain,rangeGain,hpLvl,hpXPGain,hpGain,prayLvl,prayXPGain,prayGain;

    private String currentTask;

    private double hpToLvl, slayToLvl, attToLvl, strToLvl, defToLvl, rangeToLvl, mageToLvl, prayToLvl;

    private OpiaSpiritualMages bot;

    public SlayerUI(OpiaSpiritualMages bot) {
        // Load the fxml file using RuneMate's resources class.
        FXMLLoader loader = new FXMLLoader();
        this.bot = bot;

        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/ethan0pia/bots/SlayerBot/ui/SlayerUI.fxml"));

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

        totalGP= gph= attXPGain= attGain=slayXPGain=slayGain=strXPGain=strGain=defXPGain=
                defGain= magicXPGain=magicGain=rangeXPGain=rangeGain=hpXPGain=hpGain=prayXPGain=prayGain=0;

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setVisible(true);

    }

/********************************************************************************************************************************************************
//-----------------------------------------------------------------updates--UI-----------------------------------------------------------------------/
 ******************************************************************************************************************************************************/
    public void update(int att, int slay, int def, int str, int mage, int range, int hp, int pray){
        attGain =att;
        slayGain=slay;
        defGain=def;
        strGain=str;
        magicGain=mage;
        rangeGain=range;
        hpGain=hp;
        prayGain=pray;
    }

    public void update1(int totalGP, int gph, int attLvl, int attXPGain, int slayLvl, int slayXPGain, int strLvl, int strXPGain, int defLvl, int defXPGain
            , int magicLvl, int magicXPGain, int rangeLvl, int rangeXPGain, int hpLvl, int hpXPGain, int prayLvl, int prayXPGain, String currentTask,
                        double hpToLvl, double slayToLvl, double attToLvl, double strToLvl, double defToLvl, double rangeToLvl, double mageToLvl, double prayToLvl) {

        this.attLvl=attLvl;
        this.slayLvl=slayLvl;
        this.strLvl=strLvl;
        this.defLvl=defLvl;
        this.magicLvl=magicLvl;
        this.rangeLvl=rangeLvl;
        this.hpLvl=hpLvl;
        this.prayLvl=prayLvl;
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

    public void update3(String runTime){

        if(uiRunetime!=null){
            uiRunetime.textProperty().set(runTime);
        }
    }
}