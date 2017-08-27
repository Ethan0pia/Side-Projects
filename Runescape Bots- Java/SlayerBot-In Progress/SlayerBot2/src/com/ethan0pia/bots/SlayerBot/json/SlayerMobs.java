package com.ethan0pia.bots.SlayerBot.json;
//source:https://stackoverflow.com/questions/37001421/convert-json-file-to-string

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlayerMobs {

    @SerializedName("Mob")
    @Expose
    private List<Mob>  mob = null;

    public List<Mob> getMob() {
        return  mob;
    }

    public void setMob(List<Mob>  mob) {
        this. mob =  mob;
    }
}
