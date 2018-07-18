package com.ethan0pia.bots.QBDBot.EatingThread;


public class ThreadCreator implements Runnable {

    public void run() {
        new EatBotThread().start();
    }
}
