package com.ethan0pia.bots.SlayerBot.mobs;


import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;


public class SlayerMobs {

    private String mobName[];
    private Area mobArea[];
    private Coordinate webCoord[];
    private Path paths[][];
    private String interactions[][];


    public String getMobName(int index) {
        return mobName[index];
    }

    public Area getMobArea(int index) {
        return mobArea[index];
    }

    public Coordinate getWebCoord(int index) {
        return webCoord[index];
    }

    public Path getPaths(int x, int y){
        return paths[x][y];
    }


    public SlayerMobs() {
        mobName = new String[130];
        mobArea = new Area[130];
        webCoord = new Coordinate[130];
        paths = new Path[130][5];
        interactions = new String[130][10];


        mobName[2] = "Goblin";
        mobArea[2] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[2] = new Coordinate(0,0,0);



        mobName[4] = "Giant spider";
        mobArea[4] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[4] = new Coordinate(0,0,0);



        mobName[5] = "Chicken";
        mobArea[5] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[5] = new Coordinate(0,0,0);



        mobName[6] = "Cow";
        mobArea[6] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[6] = new Coordinate(0,0,0);



        mobName[7] = "Scorpion";
        mobArea[7] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[7] = new Coordinate(0,0,0);



        mobName[9] = "Wolf";
        mobArea[9] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[9] = new Coordinate(0,0,0);



        mobName[10] = "Zombie";
        mobArea[10] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[10] = new Coordinate(0,0,0);



        mobName[13] = "Black bear";
        mobArea[13] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[13] = new Coordinate(0,0,0);



        mobName[18] = "Troll grunt";
        mobArea[18] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[18] = new Coordinate(0,0,0);


        mobName[39] = "Crawling hand";
        mobArea[39] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[39] = new Coordinate(0,0,0);



        mobName[111] = "Gelatinous Abomination";
        mobArea[111] =  new Area.Rectangular(new Coordinate(0,0,0), new Coordinate(0,0,0));
        webCoord[111] = new Coordinate(0,0,0);

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