package com.ethan0pia.bots.SlayerBot.mobs;


import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;


public class SlayerMobs {

    private String mobName[];
    private String taskName[];
    private Area mobArea[];
    private Coordinate webCoord[];
    private String finishingBlowName[];
    private String specialItem[];
    private byte combatType[];//1 melee, 2 ranged, 3 air spells, 4 water spells, 5 earth spells, 6 fire spells

    public String getMobName(int x) {
        return mobName[x];
    }

    public String getTaskName(int x) {
        return taskName[x];
    }

    public Area getMobArea(int x) {
        return mobArea[x];
    }

    public Coordinate getWebCoord(int x) {
        return webCoord[x];
    }

    public String getFinishingBlowName(int x) {
        return finishingBlowName[x];
    }

    public String getSpecialItem(int x) {
        return specialItem[x];
    }

    public byte getCombatType(int x) {
        return combatType[x];
    }


    public Area masters(String master){
        switch(master){
            case "Turael":
            case "Spria":
                Area area= new Area.Rectangular(new Coordinate(2909, 3424,0), new Coordinate(2914, 3420,0));
                return area;
        }

        return null;
    }




    public SlayerMobs() {
        mobName = new String[112];
        mobArea = new Area[112];
        webCoord = new Coordinate[112];
        taskName = new String[112];
        finishingBlowName = new String[112];
        specialItem = new String[112];
        combatType = new byte[112];



        mobName[2] = "Goblin";
        taskName[2] = "Goblins";
        mobArea[2] =  new Area.Rectangular(new Coordinate(2993, 3217, 0), new Coordinate(3007, 3201,0));
        webCoord[2] = mobArea[2].getRandomCoordinate();
        combatType[2] = 3;


        mobName[4] = "Giant spider";
        taskName[4] = "Spiders";
        mobArea[4] =  new Area.Rectangular(new Coordinate(3184, 3194,0), new Coordinate(3196, 3182,0));
        webCoord[4] = mobArea[4].getRandomCoordinate();
        combatType[4] = 1;


        mobName[5] = "Seagull";
        taskName[5] = "Birds";
        mobArea[5] = new Area.Rectangular(new Coordinate(3023,3241,0), new Coordinate(3035,3229,0));
        webCoord[5] = mobArea[5].getCenter();
        combatType[5] = 3;


        mobName[6] = "Cow";
        taskName[6] = "Cows";
        mobArea[6] =  new Area.Rectangular(new Coordinate(2882, 3492,0), new Coordinate(2889, 3483,0));
        webCoord[6] = mobArea[6].getRandomCoordinate();
        combatType[6] = 3;


        mobName[7] = "Scorpion";
        taskName[7] = "Scorpions";
        mobArea[7] =  new Area.Rectangular(new Coordinate(3296, 3314,0), new Coordinate(3302, 3297,0));
        webCoord[7] = mobArea[7].getRandomCoordinate();
        combatType[7] = 1;


        mobName[9] = "White wolf";
        taskName[9] = "Wolves";
        mobArea[9] =  new Area.Rectangular(new Coordinate(2879, 3524,0), new Coordinate(2886, 3511,0));
        webCoord[9] = mobArea[9].getRandomCoordinate();
        combatType[9] = 4;


        mobName[10] = "Zombie";
        taskName[10] = "Zombies";
        mobArea[10] =  new Area.Rectangular(new Coordinate(3139,9906,0), new Coordinate(3151,9880, 0));
        webCoord[10] = new Coordinate(3092,3485,0);
        combatType[10] = 5;

        mobName[11] = "Skeleton";
        taskName[11] = "Skeletons";
        mobArea[11] =  new Area.Rectangular(new Coordinate(3094,9909,0), new Coordinate(3107,9886,0));
        webCoord[11] = new Coordinate(3092,3485,0);
        combatType[11] = 5;

        mobName[13] = "Black bear";
        taskName[13] = "Bears";
        mobArea[13] =  new Area.Rectangular(new Coordinate(2962, 3477,0), new Coordinate(2976, 3452,0));
        webCoord[13] = mobArea[13].getRandomCoordinate();
        combatType[13] = 4;


        mobName[18] = "Troll chucker";
        taskName[18] = "Trolls";
        mobArea[18] =  new Area.Rectangular(new Coordinate(2201, 4405,0), new Coordinate(2214, 4394,0));
        webCoord[18] = new Coordinate(2878, 3572,0);
        combatType[18] = 1;


        mobName[39] = "Crawling hand";
        taskName[39] = "Crawling Hands";
        mobArea[39] =  new Area.Rectangular(new Coordinate(2208, 4581,0), new Coordinate(2228, 4572,0));
        webCoord[39] = new Coordinate(2927, 3407,0);
        combatType[39] = 6;


        mobName[111] = "Gelatinous abomination";
        taskName[111] = "Gelatinous Abominations";
        mobArea[111] =  new Area.Rectangular(new Coordinate(2193, 4521,0), new Coordinate(2203, 4507,0));
        webCoord[111] = new Coordinate(2927, 3407,0);
        specialItem[111]="Spiked gauntlets";
        combatType[111] = 2;
        finishingBlowName[111] = "Crush";


    }


}
/*
2 is goblin
4 spiders
5 birds
6 is cow
7 scorpions
9 wolves
10 zombies
13 bears
18 trolls
39 crawling hands
111 Gelatinous Abominations
*/