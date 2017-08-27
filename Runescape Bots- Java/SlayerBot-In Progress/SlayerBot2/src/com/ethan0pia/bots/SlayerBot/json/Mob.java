package com.ethan0pia.bots.SlayerBot.json;
//source:https://stackoverflow.com/questions/37001421/convert-json-file-to-string

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.runemate.game.api.hybrid.location.Coordinate;

public class Mob {

    /*
      "name": "",
      "index": 0,
      "nW": ( 0, 0, 0 ),
      "sE": [ 0, 0, 0 ],
      "specialItem": "",
      "gameObjects": [ "object name", "action" ],
      "safeSpot": [ 0, 0, 0 ],
      "path1": []
     */

    @SerializedName("index")
    @Expose
    private int iD;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nW")
    @Expose
    private List<Coordinate> nW = null;
    @SerializedName("sE")
    @Expose
    private List<Coordinate> sE = null;
    @SerializedName("specialItem")
    @Expose
    private String specialItem;
    @SerializedName("gameObjects")
    @Expose
    private List<String> gameObjects;
    @SerializedName("safeSpot")
    @Expose
    private Coordinate safeSpot = null;
    @SerializedName("path1")
    @Expose
    private List<Coordinate> path1 = null;
    @SerializedName("path2")
    @Expose
    private List<Coordinate> path2 = null;
    @SerializedName("path3")
    @Expose
    private List<Coordinate> path3 = null;
    @SerializedName("path4")
    @Expose
    private List<Coordinate> path4 = null;
    @SerializedName("path5")
    @Expose
    private List<Coordinate> path5 = null;

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coordinate> getnW() {
        return nW;
    }

    public void setnW(List<Coordinate> nW) {
        this.nW = nW;
    }

    public List<Coordinate> getsE() {
        return sE;
    }

    public void setsE(List<Coordinate> sE) {
        this.sE = sE;
    }

    public String getSpecialItem() {
        return specialItem;
    }

    public void setSpecialItem(String specialItem) {
        this.specialItem = specialItem;
    }

    public List<String> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<String> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Coordinate getSafeSpot() {
        return safeSpot;
    }

    public void setSafeSpot(Coordinate safeSpot) {
        this.safeSpot = safeSpot;
    }

    public List<Coordinate> getPath1() {
        return path1;
    }

    public void setPath1(List<Coordinate> path1) {
        this.path1 = path1;
    }

    public List<Coordinate> getPath2() {
        return path2;
    }

    public void setPath2(List<Coordinate> path2) {
        this.path2 = path2;
    }

    public List<Coordinate> getPath3() {
        return path3;
    }

    public void setPath3(List<Coordinate> path3) {
        this.path3 = path3;
    }

    public List<Coordinate> getPath4() {
        return path4;
    }

    public void setPath4(List<Coordinate> path4) {
        this.path4 = path4;
    }

    public List<Coordinate> getPath5() {
        return path5;
    }

    public void setPath5(List<Coordinate> path5) {
        this.path5 = path5;
    }
}

