package com.ethan0pia.bots.SlayerBot.ui;

public class Info {

    public int tasksComplete, totalGP, gph, attLvl, attXPGain, attGain,slayLvl,slayXPGain,slayGain,strLvl,strXPGain,strGain,defLvl,defXPGain,
    defGain,magicLvl, magicXPGain,magicGain,rangeLvl,rangeXPGain,rangeGain,hpLvl,hpXPGain,hpGain,prayLvl,prayXPGain,prayGain;
    public String runTime, currentTask, slayerMaster;

    public Info(int att, int slay, int def, int str, int mage, int range, int hp, int pray) {
        tasksComplete= totalGP= gph= attXPGain= attGain=slayXPGain=slayGain=strXPGain=strGain=defXPGain=
                defGain= magicXPGain=magicGain=rangeXPGain=rangeGain=hpXPGain=hpGain=prayXPGain=prayGain=0;
        attLvl=att;
        slayLvl=slay;
        defLvl=def;
        strLvl=str;
        magicLvl=mage;
        rangeLvl=range;
        hpLvl=hp;
        prayLvl=pray;
    }

    public void update(int tasksComplete, int totalGP, int gph, int attLvl, int attXPGain, int slayLvl, int slayXPGain, int strLvl, int strXPGain, int defLvl, int defXPGain
                , int magicLvl, int magicXPGain, int rangeLvl, int rangeXPGain, int hpLvl, int hpXPGain, int prayLvl, int prayXPGain, String runTime, String currentTask) {
        if(this.attLvl<attLvl) {
            attGain++;
        }
        if(this.slayLvl<slayLvl){
            slayGain++;
        }
        if(this.strLvl<strLvl){
            strGain++;
        }
        if(this.defLvl<defLvl){
            defGain++;
        }
        if(this.magicLvl<magicLvl){
            magicGain++;
        }
        if(this.rangeLvl<rangeLvl){
            rangeGain++;
        }
        if(this.hpLvl<hpLvl){
            hpGain++;
        }
        if(this.prayLvl<prayLvl){
            prayGain++;
        }

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
    }
}