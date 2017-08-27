package com.ethan0pia.bots.SlayerBot.json;
//source:https://stackoverflow.com/questions/37001421/convert-json-file-to-string

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHeader {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader("com/ethan0pia/bots/SlayerBot/SlayerMobs.json"));
            Gson gson = new GsonBuilder().create();
            SlayerMobs results = gson.fromJson(reader, SlayerMobs.class);

            if(results != null){
                for(Mob h : results.getMob()){
                    String temp =("ID: "+h.getiD()+" Name: "+h.getName());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
}
