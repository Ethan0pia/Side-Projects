package com.ethan0pia.bots.SlayerBot.ui;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Label uiRunetime, uiSlayerMaster, uiTasksCompleted, uiCurrentTask,uiTotalGP,uiGPH,uiAttackLevel,uiAttackXPGain,uiAttackLevelGain,
            uiSlayerLevel,uiSlayerXPGain,uiSlayerLevelGain,uiStrengthLevel,uiStrengthXPGain,uiStrengthLevelGain,uiDefenseLevel,uiDefenseXPGain,
            uiDefenseLevelGain,uiMagicLevel,uiMagicXPGain,uiMagicLevelGain,uiRangeLevel,uiRangeXPGain,uiRangeLevelGain,uiHPLevel,uiHPXPGain,
            uiHPLevelGain,uiPrayerLevel,uiPrayerXPGain,uiPrayerLevelGain, uiMonstersLeft;

    @FXML
    private ProgressBar ConstitutionStatusBar, PrayerStatusBar, DefenseStatusBar, MagicStatusBar, RangedStatusBar, StrengthStatusBar, AttackStatusBar, SlayerStatusBar;

    @FXML
    private Button start_BT,loadCurrent,savePreset;


    @FXML//all the damn fucking retarded ass settings to choose what preset to use on which monster. Numbers match their varbit code for
    //that task and the order reflects their location in t he settings txt.
    private ChoiceBox<Integer> cb1,cb2,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13,cb18,cb22,cb39,cb57,cb62,cb76,cb111,cb112,cb38,cb75,
            cb63,cb47,cb33,cb64,cb70,cb44,cb17,cb79,cb34,cb20,cb19,cb14,cb15,cb65,cb48,cb50,cb36,cb28,cb43,cb23,cb40,cb41,cb24,cb55,cb53,
            cb16,cb74,cb25,cb58,cb99,cb45, cb37, cb103, cb46, cb26, cb59, cb29, cb95, cb89, cb31, cb52, cb30, cb27, cb104, cb109;

    @FXML
    private ComboBox<String> food, slayerMaster, minVal, minValStack, spellType;

    @FXML
    private ComboBox<Integer> preset,abilityBar;

    @FXML
    private CheckBox alchBox, goldCharms, greenCharms, crimsonCharms, blueCharms;

    @FXML
    private TextField helm, body, legs, boots, gloves, cape, neck, ring, mainHand, offHand,ammunition, pocket,foodQuantity;


    private TextField gearStuff[];

    private ComboBox comboBoxes[];

    private CheckBox checkBoxes[];

   private ChoiceBox[] gearSettup;

    private int tasksComplete, totalGP, gph, attLvl, attXPGain, attGain,slayLvl,slayXPGain,slayGain,strLvl,strXPGain,strGain,defLvl,defXPGain,
            defGain,magicLvl, magicXPGain,magicGain,rangeLvl,rangeXPGain,rangeGain,hpLvl,hpXPGain,hpGain,prayLvl,prayXPGain,prayGain, monstersLeft, totalTask;
    private String currentTask;

    private double hpToLvl, slayToLvl, attToLvl, strToLvl, defToLvl, rangeToLvl, mageToLvl, prayToLvl;
    private String currentGear[]={"head","torso","legs","boots","gloves","cape","neck","ring","off-hand","ammo","pocket","main-hand"};

    private OpiaSlayer bot;

    public SlayerUI(OpiaSlayer bot) {
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

        tasksComplete= totalGP= gph= attXPGain= attGain=slayXPGain=slayGain=strXPGain=strGain=defXPGain=
                defGain= magicXPGain=magicGain=rangeXPGain=rangeGain=hpXPGain=hpGain=prayXPGain=prayGain=monstersLeft=0;

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        setVisible(true);
        slayerMaster.getItems().addAll("Turael", "Spria", "Vannaka","Chaeldar","Sumona","Duradel","Lapalok");
        setDefaults(slayerMaster);
        food.getItems().addAll("Traut", "Salmon", "Lobster", "Monkfish", "Shark");
        setDefaults(food);
        minVal.getItems().addAll("0", "250","500","1000","1500","2500","5000","7500","10000","20000","30000","40000","50000","100000","1000000");
        setDefaults(minVal);
        minValStack.getItems().addAll("0", "25","50","100","150","200","300","400","500","1000","1500","2500","5000","1000000");
        setDefaults(minValStack);
        preset.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        spellType.getItems().addAll("None","Air","Earth","Water","Fire");
        setDefaults(spellType);
        abilityBar.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        setDefaults(abilityBar);

        comboBoxes=new ComboBox[]{slayerMaster, minVal, minValStack};
        checkBoxes=new CheckBox[]{alchBox, goldCharms, greenCharms, crimsonCharms, blueCharms};

        //make a god damn fucking array to loop through and assign the correct setting for each god damn fucking monster.
        gearSettup=new ChoiceBox[]{cb1,cb2,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13,cb18,cb22,cb39,cb57,cb62,cb76,cb111,cb112,
                cb38,cb75,cb63,cb47,cb33,cb64,cb70,cb44,cb17,cb79,cb34,cb20,cb19,cb14,cb15,cb65,cb48,cb50,cb36,cb28,cb43,cb23,cb40,
                cb41,cb24,cb55,cb53,cb16,cb74,cb25,cb58,cb99,cb45,cb37,cb103,cb46,cb26,cb59,cb29,cb95,cb89,cb31,cb52,cb30,cb27,cb104,cb109};

        BufferedReader out = null;
        try {
            out = new BufferedReader(new FileReader("src/com/ethan0pia/bots/SlayerBot/ui/comboBoxes.txt"));
            for(ComboBox i:comboBoxes){
                i.getSelectionModel().select(Integer.parseInt(out.readLine().trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out = new BufferedReader(new FileReader("src/com/ethan0pia/bots/SlayerBot/ui/mobSettings.txt"));
            for(ChoiceBox i : gearSettup){
                i.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
                i.getSelectionModel().select(Integer.parseInt(out.readLine().trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out = new BufferedReader(new FileReader("src/com/ethan0pia/bots/SlayerBot/ui/checkBoxes.txt"));
            for(CheckBox i : checkBoxes){
                i.setSelected(Boolean.parseBoolean((out.readLine().trim())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        start_BT.setOnAction(getStart_BTAction());
        loadCurrent.setOnAction(getLeadGearAction());
        savePreset.setOnAction(getSavePreset());
        preset.setOnAction(getLoadPreset());
    }

    private void setDefaults(ComboBox box){
        box.getSelectionModel().selectFirst();
    }

   private EventHandler<ActionEvent> getLeadGearAction() {
        return event -> {
            try {
                String[] gear = new String[12];
                for(int i=0;i<12;i++){
                    if(bot.getRoot().getGear()[i]!=null){
                        gear[i]=bot.getRoot().getGear()[i];
                    }else{
                        gear[i]=" ";
                    }
                }
                gearStuff = new TextField[]{helm, body, legs, boots, gloves, cape, neck, ring, offHand, ammunition, pocket, mainHand, foodQuantity};
                int i = 0;
                for (String y : gear) {
                    if (y != null) {
                        currentGear[i] = y;
                    }
                    if (currentGear[i] != null) {
                        gearStuff[i].setText(currentGear[i]);
                    }
                    i++;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> getSavePreset() {
        return event -> {
            try {
                gearStuff=new TextField[]{helm, body, legs, boots, gloves, cape, neck, ring, offHand,ammunition, pocket, mainHand,foodQuantity};

                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new FileWriter("src/com/ethan0pia/bots/SlayerBot/ui/preset"+preset.getSelectionModel().getSelectedIndex()+".txt"));
                    for(TextField i : gearStuff){
                        if(i.getText()==null||i.getText().equals(" ")){
                            out.write(" "+'\n');
                        }else {
                            out.write(i.getText() + '\n');
                        }
                    }
                    out.write(""+String.valueOf(food.getSelectionModel().getSelectedItem())+'\n'+String.valueOf(spellType.getSelectionModel().getSelectedIndex())+'\n'+String.valueOf(abilityBar.getSelectionModel().getSelectedItem())+'\n');
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(out != null){
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> getLoadPreset() {
        return event -> {
            try {
                gearStuff=new TextField[]{helm, body, legs, boots, gloves, cape, neck, ring, offHand, ammunition, pocket, mainHand,foodQuantity};
                BufferedReader in = null;
                    try {
                        int presetNum = preset.getSelectionModel().getSelectedIndex();
                        in = new BufferedReader(new FileReader("src/com/ethan0pia/bots/SlayerBot/ui/preset" + presetNum + ".txt"));
                        for(TextField y : gearStuff) {
                            String text = in.readLine();
                            if(text!=null) {
                                y.setText(text.trim());
                            }else{
                                y.setText(" ");
                            }
                        }
                        String read;
                        read = in.readLine();
                        if (read != null) {
                            read = read.trim();
                            food.getSelectionModel().select(read);
                        } else {
                            food.getSelectionModel().selectFirst();
                        }

                        read = in.readLine();
                        if (read != null) {
                            read = read.trim();
                            spellType.getSelectionModel().select(Integer.parseInt(read));
                        } else {
                            spellType.getSelectionModel().selectFirst();
                        }

                        read = in.readLine();
                        if (read != null) {
                            read = read.trim();
                            abilityBar.getSelectionModel().select(Integer.parseInt(read)-1);
                        } else {
                            abilityBar.getSelectionModel().selectFirst();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (in != null) {
                                in.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
        };
    }

    private EventHandler<ActionEvent> getStart_BTAction() {
        return event -> {
            try {
                if(bot.getStopWatch().isRunning()){
                    bot.getStopWatch().stop();
                    bot.setGuiWait(true);
                }
                if(alchBox.isSelected()){
                    bot.setAlch(true);
                }
                bot.setMinVal(Integer.parseInt(minVal.getSelectionModel().getSelectedItem()));
                bot.setMinValStack(Integer.parseInt(minValStack.getSelectionModel().getSelectedItem()));
                bot.setMaster(slayerMaster.getSelectionModel().getSelectedIndex());
                start_BT.textProperty().set("Update Settings");

                comboBoxes=new ComboBox[]{slayerMaster, minVal, minValStack};
                checkBoxes=new CheckBox[]{alchBox, goldCharms, greenCharms, crimsonCharms, blueCharms};

                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new FileWriter("src/com/ethan0pia/bots/SlayerBot/ui/comboBoxes.txt"));
                    for(ComboBox i : comboBoxes){
                        out.write(String.valueOf(i.getSelectionModel().getSelectedIndex())+'\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(out != null){
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    out = new BufferedWriter(new FileWriter("src/com/ethan0pia/bots/SlayerBot/ui/checkBoxes.txt"));
                    for(CheckBox i : checkBoxes) {
                        out.write(String.valueOf(i.isSelected())+'\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(out != null){
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    out = new BufferedWriter(new FileWriter("src/com/ethan0pia/bots/SlayerBot/ui/mobSettings.txt"));
                    for(ChoiceBox i : gearSettup){
                        out.write(String.valueOf(i.getSelectionModel().getSelectedIndex())+'\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(out != null){
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            bot.setGuiWait(false);
        };
    }


    public boolean isLootGoldCharms(){
        return goldCharms.isSelected();
    }

    public boolean isLootGreenCharms(){
        return greenCharms.isSelected();
    }

    public boolean isLootCrimsonCharms(){
        return crimsonCharms.isSelected();
    }

    public boolean isLootBlueCharms(){
        return blueCharms.isSelected();
    }


/********************************************************************************************************************************************************
//-----------------------------------------------------------------updates--UI-----------------------------------------------------------------------/
 ******************************************************************************************************************************************************/
    public void update(int att, int slay, int def, int str, int mage, int range, int hp, int pray, int left, int totalTask){
        attGain =att;
        slayGain=slay;
        defGain=def;
        strGain=str;
        magicGain=mage;
        rangeGain=range;
        hpGain=hp;
        prayGain=pray;
        monstersLeft=left;
        this.totalTask=totalTask;
    }

    public void update1(int tasksComplete, int totalGP, int gph, int attLvl, int attXPGain, int slayLvl, int slayXPGain, int strLvl, int strXPGain, int defLvl, int defXPGain
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
            uiSlayerMaster.textProperty().set(slayerMaster.getItems().get(bot.getMaster()).trim());
            uiTasksCompleted.textProperty().set("" + tasksComplete);
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

            uiMonstersLeft.textProperty().set("" +  monstersLeft +" / " + totalTask);


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