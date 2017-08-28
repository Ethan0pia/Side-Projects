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
    private Path paths[][];
    private String interactions[][];
    private String gameObjects[][];
    private String finishingBlowName[];
    private int pathSize[];
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

    public Path getPaths(int x, int y) {
        return paths[x][y];
    }

    public String getInteractions(int x, int y) {
        return interactions[x][y];
    }

    public String getGameObjects(int x, int y) {
        return gameObjects[x][y];
    }

    public String getFinishingBlowName(int x) {
        return finishingBlowName[x];
    }

    public int getPathSize(int x) {
        return pathSize[x];
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
        paths = new Path[112][5];
        gameObjects = new String[112][5];
        interactions = new String[112][5];
        taskName = new String[112];
        finishingBlowName = new String[112];
        pathSize = new int[112];
        specialItem = new String[112];
        combatType = new byte[112];



        mobName[2] = "Goblin";
        taskName[2] = "Goblins";
        mobArea[2] =  new Area.Rectangular(new Coordinate(2993, 3217, 0), new Coordinate(3007, 3201,0));
        webCoord[2] = mobArea[2].getRandomCoordinate();
        pathSize[2] = 0;
        combatType[2] = 3;


        mobName[4] = "Giant spider";
        taskName[4] = "Spiders";
        mobArea[4] =  new Area.Rectangular(new Coordinate(3184, 3194,0), new Coordinate(3196, 3182,0));
        webCoord[4] = mobArea[4].getRandomCoordinate();
        pathSize[4] = 0;
        combatType[4] = 1;


        mobName[5] = "Chicken";
        taskName[5] = "Birds";
        mobArea[5] = new Area.Rectangular(new Coordinate(3204, 3287,0), new Coordinate(3210, 3285,0));
        webCoord[5] = mobArea[5].getRandomCoordinate();
        pathSize[5] = 0;
        combatType[5] = 6;


        mobName[6] = "Cow";
        taskName[6] = "Cows";
        mobArea[6] =  new Area.Rectangular(new Coordinate(2882, 3492,0), new Coordinate(2889, 3483,0));
        webCoord[6] = mobArea[6].getRandomCoordinate();
        pathSize[6] = 0;
        combatType[6] = 3;


        mobName[7] = "Scorpion";
        taskName[7] = "Scorpions";
        mobArea[7] =  new Area.Rectangular(new Coordinate(3296, 3314,0), new Coordinate(3302, 3297,0));
        webCoord[7] = mobArea[7].getRandomCoordinate();
        pathSize[7] = 0;
        combatType[7] = 1;


        mobName[9] = "Wolf";
        taskName[9] = "Wolves";
        mobArea[9] =  new Area.Rectangular(new Coordinate(1868, 5243,0), new Coordinate(1875, 5227,0));
        webCoord[9] = new Coordinate (3081, 3421, 0);
        gameObjects[9][0]="Entrance";
        interactions[9][0]="Climb-down";
        paths[9][0]= PredefinedPath.create(new Coordinate(1859, 5239, 0));
        gameObjects[9][1]="Gate of War";
        interactions[9][1]="Open";
        paths[9][1]= PredefinedPath.create(new Coordinate(1859, 5236, 0));
        gameObjects[9][2]="Gate of War";
        interactions[9][2]="Open";
        paths[9][2]= PredefinedPath.create(new Coordinate(1864, 5227, 0));
        gameObjects[9][3]="Gate of War";
        interactions[9][3]="Open";
        paths[9][3]= PredefinedPath.create(new Coordinate(1867, 5227, 0));
        gameObjects[9][4]="Gate of War";
        interactions[9][4]="Open";
        paths[9][4]= PredefinedPath.create(mobArea[9].getRandomCoordinate());
        pathSize[9] = 5;
        combatType[9] = 4;


        mobName[10] = "Zombie";
        taskName[10] = "Zombies";
        mobArea[10] =  new Area.Rectangular(new Coordinate(2026, 5238,0), new Coordinate(2034, 5231,0));
        webCoord[10] = new Coordinate (3081, 3421, 0);
        gameObjects[10][0]="Entrance";
        interactions[10][0]="Climb-down";
        paths[10][0]= PredefinedPath.create(new Coordinate(1863, 5239, 0));
        gameObjects[10][1]="Portal";
        interactions[10][1]="Enter";
        paths[10][1]= PredefinedPath.create(new Coordinate(1904, 5221, 0));
        gameObjects[10][2]="Ladder";
        interactions[10][2]="Climb-down";
        paths[10][2]= PredefinedPath.create(new Coordinate(2040, 5244, 0));
        gameObjects[10][3]="Rickety door";
        interactions[10][3]="Open";
        paths[10][3]= PredefinedPath.create(new Coordinate(2037, 5244, 0));
        gameObjects[10][4]="Rickety door";
        interactions[10][4]="Open";
        paths[10][4]= PredefinedPath.create(mobArea[10].getRandomCoordinate());
        pathSize[10] = 5;
        combatType[10] = 5;


        mobName[13] = "Black bear";
        taskName[13] = "Bears";
        mobArea[13] =  new Area.Rectangular(new Coordinate(2962, 3477,0), new Coordinate(2976, 3452,0));
        webCoord[13] = mobArea[13].getRandomCoordinate();
        pathSize[13] = 0;
        combatType[13] = 4;


        mobName[18] = "Troll chucker";
        taskName[18] = "Trolls";
        mobArea[18] =  new Area.Rectangular(new Coordinate(2201, 4405,0), new Coordinate(2214, 4394,0));
        webCoord[18] = new Coordinate(2878, 3572,0);
        gameObjects[18][0]="Cave entrance";
        interactions[18][0]="Enter";
        paths[18][0]= RegionPath.buildTo(mobArea[18].getRandomCoordinate());
        pathSize[18] = 1;
        combatType[18] = 1;


        mobName[39] = "Crawling hand";
        taskName[39] = "Crawling Hands";
        mobArea[39] =  new Area.Rectangular(new Coordinate(2208, 4581,0), new Coordinate(2228, 4572,0));
        webCoord[39] = new Coordinate(2927, 3407,0);
        gameObjects[39][0]="Cave Entrance";
        interactions[39][0]="Enter";
        paths[39][0]= RegionPath.buildTo(mobArea[39].getRandomCoordinate());
        pathSize[39] = 1;
        combatType[39] = 6;


        mobName[111] = "Gelatinous Abomination";
        taskName[111] = "Gelatinous Abominations";
        mobArea[111] =  new Area.Rectangular(new Coordinate(2193, 4521,0), new Coordinate(2203, 4507,0));
        webCoord[111] = new Coordinate(2927, 3407,0);
        gameObjects[111][0]="Cave Entrance";
        interactions[111][0]="Enter";
        paths[111][0]= RegionPath.buildTo(mobArea[111].getRandomCoordinate());
        pathSize[111] = 1;
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