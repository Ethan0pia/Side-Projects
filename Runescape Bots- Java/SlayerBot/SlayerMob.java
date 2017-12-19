package com.ethan0pia.bots.SlayerBot;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SlayerMob {
    MONKEYS(1, "Monkey", "Monkeys", new Area.Circular(new Coordinate(2883,3158,0),15), new Coordinate(2812,3183,0), 0, null, true, false, "Gate", "Open", new Area.Rectangular(new Coordinate(2815,3204,0),new Coordinate(2897,3142,0)),new String[16]),
    GOBLINS(2,"Goblin", "Goblins", new Area.Rectangular(new Coordinate(2993, 3217, 0), new Coordinate(3007, 3201,0)),new Area.Rectangular(new Coordinate(2993, 3217, 0), new Coordinate(3007, 3201,0)).getRandomCoordinate(), 0,null, false, false, null,null,null,new String[16]),
    SPIDERS(4, "Giant spider", "Spiders", new Area.Rectangular(new Coordinate(3184, 3194,0), new Coordinate(3196, 3182,0)), new Area.Rectangular(new Coordinate(3184, 3194,0), new Coordinate(3196, 3182,0)).getRandomCoordinate(),0,null,false,false,null,null,null,new String[16]),
    BIRDS(5,"Seagull", "Birds", new Area.Rectangular(new Coordinate(3023,3241,0), new Coordinate(3035,3229,0)), new Area.Rectangular(new Coordinate(3023,3241,0), new Coordinate(3035,3229,0)).getCenter(),0,null,false,false,null,null,null,new String[16]),
    COWS(6,"Cow", "Cows", new Area.Rectangular(new Coordinate(2882, 3492,0), new Coordinate(2889, 3483,0)), new Area.Rectangular(new Coordinate(2882, 3492,0), new Coordinate(2889, 3483,0)).getRandomCoordinate(), 0,null,false,false,null,null,null,new String[16]),
    SCORPIONS(7,"Scorpion", "Scorpions", new Area.Rectangular(new Coordinate(3296, 3314,0), new Coordinate(3302, 3297,0)), new Area.Rectangular(new Coordinate(3296, 3314,0), new Coordinate(3302, 3297,0)).getRandomCoordinate(),0,null,false,false,null,null,null,new String[16]),
    BATS(8,"Bat", "Bats",new Area.Rectangular(new Coordinate(3336,3497,0), new Coordinate(3383,3474,0)), new Area.Rectangular(new Coordinate(3336,3497,0), new Coordinate(3383,3474,0)).getRandomCoordinate(),0,null,false,false,null,null,null,new String[16]),
    WOLVES(9,"White wolf", "Wolves", new Area.Rectangular(new Coordinate(2879, 3524,0), new Coordinate(2886, 3511,0)), new Area.Rectangular(new Coordinate(2879, 3524,0), new Coordinate(2886, 3511,0)).getRandomCoordinate(),0,null,false,false,null,null,null,new String[16]),
    ZOMBIES(10,"Zombie", "Zombies", new Area.Rectangular(new Coordinate(3139,9906,0), new Coordinate(3151,9880, 0)), new Coordinate(3092,3485,0),  0,null,false, true,null,null,null,new String[16]),
    SKELETONS(11, "Skeleton", "Skeletons", new Area.Rectangular(new Coordinate(3094,9909,0), new Coordinate(3107,9886,0)),new Coordinate(3092,3485,0),  0,null, false, true, null, null, null,new String[16]),
    GHOSTS(12, "Ghost", "Ghosts", new Area.Rectangular(new Coordinate(2889,9851,0), new Coordinate(2922,9846,0)),new Coordinate(2905,9849,0),0,null,false,false,null,null,null,new String[16]),
    BEARS(13, "Black bear", "Bears", new Area.Rectangular(new Coordinate(2962, 3477,0), new Coordinate(2976, 3452,0)),new Coordinate(2972,3464,0),0,null,false,false,null,null,null,new String[16]),
    TROLLS(18,"Troll chucker", "Trolls", new Area.Rectangular(new Coordinate(2201, 4405,0), new Coordinate(2214, 4394,0)),new Coordinate(2878, 3572,0),0,null, true,false, "Cave entrance","Enter",new Area.Circular( new Coordinate(2214, 4394,0), 250),new String[16]),
    DOGS(22,"Guard dog", "Dogs", new Area.Rectangular(new Coordinate(2624,3493,0), new Coordinate(2668,3470,0)), new Coordinate(2659,3500,0),  0,null, true, false, "Loose Railing", "Squeeze-through", new Area.Rectangular(new Coordinate(2662,3501,0),new Coordinate(2668,3470,0)),new String[16]),
    CRAWLING_HANDS(39, "Crawling hand", "Crawling Hands", new Area.Rectangular(new Coordinate(2208, 4581,0), new Coordinate(2228, 4572,0)), new Coordinate(2927, 3407,0), 0,null, true,false, "Cave Entrance", "Enter", new Area.Circular( new Coordinate(2217, 4532, 0), 250),new String[16]),
    DWARVES(57,"Dwarf", "Dwarves", new Area.Circular(new Coordinate(2599,3057,0), 15), new Area.Circular(new Coordinate(2599,3057,0), 15).getRandomCoordinate(),0,null,false,false,null,null,null,new String[16]),
    CAVE_SLIMES(62, "Cave slime", "Cave slimes", new Area.Rectangular(new Coordinate(3146,9565,0), new Coordinate(3160,9546,0)), new Area.Circular(new Coordinate(3189,3160,0),3).getRandomCoordinate(), 594,null, true ,false,"Dark hole under tree", "Climb down", new Area.Circular( new Coordinate(3160,9546,0), 300),new String[16]),
    MINOTAURS(76,"Minotaur", "Minotaurs", new Area.Rectangular(new Coordinate(1870,5221,0),new Coordinate(1882,5210,0)),new Area.Circular(new Coordinate(3081,3421,0),6).getRandomCoordinate(),0,null,false,true,null,null,null,new String[16]),
    GELATINOUS_ABOMINATIONS(111,"Gelatinous abomination","Gelatinous Abominations",new Area.Rectangular(new Coordinate(2193, 4521,0), new Coordinate(2203, 4507,0)),new Coordinate(2927, 3407,0),23035,"Crush",true,false, "Cave Entrance", "Enter", new Area.Circular( new Coordinate(2217, 4532, 0), 250),new String[16]),
    GROTWORMS(112,"Young grotworm", "Grotworms", new Area.Rectangular(new Coordinate(1145,6380,0), new Coordinate(1174,6352,0)), new Area.Rectangular(new Coordinate(1145,6380,0), new Coordinate(1174,6352,0)).getRandomCoordinate(),0,null,false,false,null,null,null,new String[16]),
    BANSHEES(38,"Banshee","Banshees",new Area.Rectangular(new Coordinate(3433,3573,0), new Coordinate(3446,3544,0)),new Area.Rectangular(new Coordinate(3433,3573,0), new Coordinate(3446,3544,0)).getRandomCoordinate(),4166,null,false,false,null,null,null,new String[16]),
    ICEFIENDS(75, "Icefiend", "Icefiends", new Area.Rectangular(new Coordinate(2999,3477,0), new Coordinate(3014,3468,0)),new Area.Rectangular(new Coordinate(2999,3477,0), new Coordinate(3014,3468,0)).getRandomCoordinate(),0,null,false,false,null,null,null,new String[16]),
    CAVE_BUGS(63, "Cave bug", "Cave bugs", new Area.Rectangular(new Coordinate(2203,4564,0), new Coordinate(2227,4545,0)), new Coordinate(2927, 3407,0),0,null, true,false, "Cave Entrance", "Enter", new Area.Circular( new Coordinate(2217, 4532, 0), 250),new String[16]),
    PYREFIENDS(47,"Pyrefiend","Pyrefiends", new Area.Circular(new Coordinate(2761,10004,0), 8), new Area.Circular(new Coordinate(2761,10004,0), 8).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    WEREWOLVES(33, "Werewolf","Werewolves", new Area.Circular(new Coordinate(3490,3491,0),20), new Coordinate(3490,3491,0),0, null,false,false,null,null,null,new String[16]),
    SHADES(64,"Shade","Shades", new Area.Circular(new Coordinate(2361,5213,0), 6),new Area.Circular(new Coordinate(3081,3421,0),6).getRandomCoordinate(),0,null,false,true,null,null,null,new String[16]),
    HARPIE_BUG_SWARMS(70,"Harpie Bug Swarm","Harpie Bug Swarms",new Area.Circular(new Coordinate(2873,3110,0), 20),new Area.Circular(new Coordinate(2873,3110,0), 20).getRandomCoordinate(),7053, null,false,false,null,null,null,new String[16]),
    COCKATRICE(44,"Cockatrice","Cockatrices",new Area.Circular(new Coordinate(2792,10036,0), 12),new Area.Circular(new Coordinate(2792,10036,0), 12).getRandomCoordinate(),4156,null,false,false,null,null,null,new String[16]),
    MOSS_GIANTS(17,"Moss giant","Moss Giants", new Area.Circular(new Coordinate(2554,3408,0), 10),new Area.Circular(new Coordinate(2554,3408,0), 10).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    ANKOUS(79,"Ankou","Ankous", new Area.Rectangular(new Coordinate(2356,5246,0), new Coordinate(2365,5235,0)),new Area.Circular(new Coordinate(3081,3421,0),6).getRandomCoordinate(),0,null,false,true,null,null,null,new String[16]),
    VAMPIRES(34,"Feral vampyre", "Vampires", new Area.Circular(new Coordinate(3598,3478,0),18),new Area.Circular(new Coordinate(3598,3478,0),8).getRandomCoordinate(),0,null,false,false,null,null,null,new String[16]),
    OGRES(20,"Ogre","Ogres",new Area.Circular(new Coordinate(2505,3113,0), 15),new Area.Circular(new Coordinate(2505,3113,0), 15).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    ICE_WARRIORS(19,"Ice warrior","Ice warriors",new Area.Circular(new Coordinate(3046,9580,0), 15),new Coordinate(3010,3150,0),0,null,true,false,"Trapdoor","Climb-down", new Area.Circular(new Coordinate(3046,9580,0),200),new String[16]),
    HILL_GIANTS(14, "Hill Giant","Hill Giants",new Area.Circular(new Coordinate(2906,9734,0), 15),new Area.Circular(new Coordinate(2906,9734,0), 15).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    ICE_GIANTS(15,"Ice giant","Ice giants",new Area.Circular(new Coordinate(3061,9572,0), 10),new Coordinate(3010,3150,0),0,null,true,false,"Trapdoor","Climb-down", new Area.Circular(new Coordinate(3046,9580,0),200),new String[16]),
    CROCODILES(65,"Crocodile","Crocodiles",new Area.Circular(new Coordinate(3306,2926,0), 14),new Area.Circular(new Coordinate(3306,2926,0), 14).getRandomCoordinate(),1823, null,false,false,null,null,null,new String[16]),
    BLOODVELD(48,"Bloodveld","Bloodvelds",new Area.Rectangular(new Coordinate(3399,3552,1), new Coordinate(3413,3541,1)),new Area.Rectangular(new Coordinate(3399,3552,1), new Coordinate(3413,3541,1)).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    JELLIES(50,"Jelly","Jellies",new Area.Circular(new Coordinate(2703,10027,0), 12),new Area.Circular(new Coordinate(2703,10027,0), 12).getCenter(),0, null,false,false,null,null,null,new String[16]),
    TUROTHS(36,"Turoth","Turoths",new Area.Circular(new Coordinate(2721,10004,0), 12),new Area.Circular(new Coordinate(2721,10004,0), 12).getRandomCoordinate(),4158, null,false,false,null,null,null,new String[16]),
    LESSER_DEMONS(28,"Lesser demon","Lesser demons",new Area.Rectangular(new Coordinate(2927,9802,0), new Coordinate(2938,9788,0)),new Area.Rectangular(new Coordinate(2927,9802,0), new Coordinate(2938,9788,0)).getCenter(),0, null,false,false,null,null,null,new String[16]),
    BASILISKS(43,"Basilisk", "Basilisks", new Area.Circular(new Coordinate(2742,10011,0), 8), new Area.Circular(new Coordinate(2742,10011,0), 8).getRandomCoordinate(),4156,null,false,false,null,null,null,new String[16]),
    GHOULS(23,"Ghoul","Ghouls",new Area.Circular(new Coordinate(3497,3538,0), 14),new Area.Circular(new Coordinate(3497,3538,0), 14).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    INFERNAL_MAGES(40,"Infernal Mage","Infernal Mages", new Area.Rectangular(new Coordinate(3400,3573,1), new Coordinate(3413,3555,1)),new Area.Rectangular(new Coordinate(3400,3573,1), new Coordinate(3413,3555,1)).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    ABERRANT_SPECTRES(41,"Aberrant spectre","Aberrant spectres", new Area.Rectangular(new Coordinate(3434,3575,1), new Coordinate(3447,3549,1)), new Area.Rectangular(new Coordinate(3434,3575,1), new Coordinate(3447,3549,1)).getRandomCoordinate(),4168,null,false,false,null,null,null,new String[16]),
    GREEN_DRAGONS(24,"Green dragon", "Green Dragons", new Area.Circular(new Coordinate(2979,3616,0),15), new Area.Circular(new Coordinate(2979,3616,0),15).getRandomCoordinate(),1540,null,false,false,null,null,null,new String[16]),
    OTHERWORLDLY_BEINGS(55,"Otherworldly being","Otherworldly beings",new Area.Circular(new Coordinate(2386,4427,0), 12),new Coordinate(3200,3169,0),0,null, false,true, null, null, null,new String[16]),
    KALPHITES(53,"Kalphite Worker", "Kalphites", new Area.Circular(new Coordinate(3441,9523,0),15),new Coordinate(3441,9523,0),0,null, false,false, null, null, null,new String[16]),
    FIRE_GIANTS(16,"Fire giant","Fire giants",new Area.Circular(new Coordinate(2664,9490,0),15),new Coordinate(2745,3152,0),0,null, false,true, null, null, null,new String[16]),
    MUTATED_ZYGOMITES(74,"Mutated Zygomite", "Mutated Zygomites", new Area.Circular(new Coordinate(2416,4470,0),10),new Coordinate(2416,4470,0),0,"Spray fungicide", false,true, null, null, null,new String[16]),
    BLUE_DRAGON(25,"Blue dragon","Blue dragons",new Area.Circular(new Coordinate(2900,9799,0), 10),new Area.Circular(new Coordinate(2900,9799,0), 15).getRandomCoordinate(),1540, null,false,true,null,null,null,new String[16]),
    BRONZE_DRAGONS(58,"Bronze dragon","Bronze Dragons",new Area.Circular(new Coordinate(2738,9489,0),10) ,new Coordinate(2745,3152,0),1540,null, false,true, null, null, null,new String[16]),
    FUNGAL_MAGES(99,"Fungal mage","Fungal Mages", new Area.Circular(new Coordinate(4651,5478,3),12),new Coordinate(4651,5478,3),0,null, false,false, null, null, null,new String[16]),
    KURASKS(45,"Kurask","Kurasks",new Area.Circular(new Coordinate(2698,9998,0), 12),new Area.Circular(new Coordinate(2698,9998,0), 12).getRandomCoordinate(),4158, null,false,false,null,null,null,new String[16]),
    CAVE_CRAWLERS(37,"Cave crawler","Cave crawlers",new Area.Circular(new Coordinate(2789,9996,0), 14),new Area.Circular(new Coordinate(2789,9996,0), 4).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    JUNGLE_STRYKEWYRMS(103,"Jungle strykewyrm","Jungle strykewyrms",new Area.Circular(new Coordinate(2448, 2902, 0), 7),new Coordinate(2450, 2903, 0),0, null,false,false,null,null,null,new String[16]),
    GARGOYLES(46,"Gargoyle","Gargoyles",new Area.Rectangular(new Coordinate(3433,3569,2), new Coordinate(3446,3543,2)),new Area.Rectangular(new Coordinate(3433,3569,2), new Coordinate(3446,3543,2)).getRandomCoordinate(),0, "Smash",false,false,null,null,null,new String[16]),
    RED_DRAGONS(26,"Red dragon","Red Dragons",new Area.Rectangular(new Coordinate(2691,9532,0),new Coordinate(2732,9501,0)) ,new Coordinate(2745,3152,0),1540,null, false,true, null, null, null,new String[16]),
    IRON_DRAGONS(59,"Iron dragon","Iron Dragons",new Area.Circular(new Coordinate(2716,9454,0),15) ,new Coordinate(2745,3152,0),1540,null, false,true, null, null, null,new String[16]),
    GREATER_DEMONS(29,"Greater demon", "Greater Demons",new Area.Circular(new Coordinate(2637,9502,2),25) ,new Coordinate(2745,3152,0),0,null, false,true, null, null, null,new String[16]),
    AQUANITES(95,"Aquanite","Aquanites",new Area.Circular(new Coordinate(2726,9971,0), 17),new Area.Circular(new Coordinate(2726,9971,0), 4).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    SPIRITUAL_WARRIORS(89, "Spiritual warrior", "Spiritual Warriors", new Area.Circular(new Coordinate(2913,5344,0), 15),new Coordinate(2908, 3708, 0),0, null,false,true,null,null,null,new String[16]),
    HELLHOUNDS(31,"Hellhound","Hellhounds",new Area.Circular(new Coordinate(2860,9842,0), 15),new Area.Circular(new Coordinate(2860,9842,0), 3).getRandomCoordinate(),0, null,false,true,null,null,null,new String[16]),
    NECHRYAELS(52, "Nechryael","Nechryaels",new Area.Rectangular(new Coordinate(3400,3573,3), new Coordinate(3413,3561,2)),new Area.Rectangular(new Coordinate(3400,3573,3), new Coordinate(3413,3561,2)).getRandomCoordinate(),0, null,false,false,null,null,null,new String[16]),
    BLACK_DEMONS(30,"Black demon","Black demons",new Area.Circular(new Coordinate(2861,9777,0), 15),new Area.Rectangular(new Coordinate(2927,9802,0), new Coordinate(2938,9788,0)).getCenter(),0, null,false,true,null,null,null,new String[16]),
    BLACK_DRAGONS(27,"Black dragon","Black dragons",new Area.Circular(new Coordinate(2834,9824,0), 12),new Area.Circular(new Coordinate(2834,9824,0), 3).getRandomCoordinate(),1540, null,false,true,null,null,null,new String[16]),
    DESERT_STRYKEWYRMS(104, "Desert strykewyrm","Desert Strykewyrms", new Area.Circular(new Coordinate(3379,3158,0), 12),new Area.Circular(new Coordinate(3379,3158,0), 12).getCenter(),0,null,false,false,null,null,null,new String[16]),
    MUTATED_JADINKOS(109,"Mutated jadinko baby","Mutated Jadinkos",new Area.Circular(new Coordinate(3018,9256,0),10),new Coordinate(2949,2955,0),0,null,true,false,"Hole","Squeeze-through",new Area.Circular(new Coordinate(3018,9256,0),70),new String[16]);
    //AIRUTS(117)
    //EARTH_WARRIORS(54,"Earth warrior","Earth warriors", , ,0,null, false, true, null, null, null,new String[16])
    //1,2,4,5,6,7,8,9,10,11,12,13,18,22,39,57,62,76,111,112,38,75,63,47,33,64,70,44,17,79,34,20,19,14,15,65,48,50,36,28,43,23,40,41,24,55,53,16,74,25,58,99,45,37,103,46,26,59,29

    /*
		 ("Desert lizard", "Desert lizards", new Area.Rectangular(new Coordinate(3410,3040,0), new Coordinate(3426,3020,0)), new Area.Rectangular(new Coordinate(3410,3040,0), new Coordinate(3426,3020,0)).getRandomCoordinate,3,null,"Cool off",false,false,null,null,null);
		 ("Pig","Pigs",new Area.Rectangular(new Coordinate(1045,5673,0), new Coordinate(1055,5656,0)),new Area.Circular(new Coordinate(3189,3160,0),5).getRandomCoordinate(),5,null,null,true,false,"Ladder", "Climb", CaveArea);
		("Abyssal demon", "Abyssal demons", new Area.Rectangular(new Coordinate(3401,3554,2), new Coordinate(3411,3541,2)), new Area.Rectangular(new Coordinate(3401,3554,2), new Coordinate(3411,3541,2)).getRandomCoordinate(),1,,null, null,false,false,null,null,null);
		 ("Dust devil","Dust devils",new Area.Circular(new Coordinate(3234,9399,0), 8),new Area.Circular(new Coordinate(3234,9399,0), 8).getRandomCoordinate(),1,"Face mask", null,false,false,null,null,null);
        //complicated path mapping needed
        ("Jungle horror","Jungle horrors",new Area.Circular(new Coordinate(3061,9572,0), 15),,5,true, );
        //complicated path mapping needed
        ("Killerwatt","Killerwatts", new Area.Circular(new Coordinate(3061,9572,0), 15),new Coordinate(3109,3360,0),1,"Insulated boots",null,false,true,null,null,null);
*/
    private int index;
    private String mobName;
    private String taskName;
    private Area mobArea;
    private Coordinate finalWebCoord;
    private String finishingBlowName;
    private int specialItem;
    private boolean basicSpecialPath = false;
    private boolean advancedSpecialPath = false;
    private String obstacle;
    private String interactionName;
    private Area caveArea;
    private String gear[];


    SlayerMob(int index, String mobName, String taskName, Area mobArea, Coordinate finalWebCoords,
              int specialItem, String finishingBlowName, boolean basicSpecialPath, boolean advancedSpecialPath,
              String obstacle, String interactionName, Area caveArea, String gear[]) {
        this.index = index;
        this.mobName=mobName;
        this.taskName=taskName;
        this.mobArea=mobArea;
        this.finalWebCoord=finalWebCoords;
        this.basicSpecialPath=basicSpecialPath;
        this.obstacle=obstacle;
        this.advancedSpecialPath=advancedSpecialPath;
        this.interactionName=interactionName;
        this.caveArea=caveArea;
        this.specialItem=specialItem;
        this.finishingBlowName=finishingBlowName;
        this.gear=gear;
    }


    public void updateGear(){
        BufferedReader out = null;
        BufferedReader preset = null;
        try {
            out = new BufferedReader(new FileReader("src/com/ethan0pia/bots/SlayerBot/ui/mobSettings.txt"));
                try {
                    for(SlayerMob i : EnumSet.allOf(SlayerMob.class)) {
                        preset = new BufferedReader(new FileReader("src/com/ethan0pia/bots/SlayerBot/ui/preset" + out.readLine().trim() + ".txt"));
                        for (int y=0;y<16;y++) {
                            i.gear[y] = preset.readLine().trim();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (preset != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
    }

    public int getCombatBar(){
        return Integer.parseInt(this.gear[15]);
    }

    public int getFoodQuantity(){
        return Integer.parseInt(gear[12]);
    }

    public String getFoodType(){
        return gear[13];
    }

    public int getSpellType(){
        return Integer.parseInt(gear[14]);
    }

    public String[] getGear(){
        return gear;
    }

    public int getIndex() { return index; }

    public String getMobName() {
        return mobName;
    }

    public String getTaskName() {
        return taskName;
    }

    public Area getMobArea() {
        return mobArea;
    }

    public Coordinate getFinalWebCoord() {
        return finalWebCoord;
    }

    public String getFinishingBlowName() {
        return finishingBlowName;
    }

    public int getSpecialItem() {
        return specialItem;
    }

    public boolean isBasicSpecialPath() {
        return basicSpecialPath;
    }

    public boolean isAdvancedSpecialPath() {
        return advancedSpecialPath;
    }

    public String getObstacle() {
        return obstacle;
    }

    public String getInteractionName() {
        return interactionName;
    }

    public Area getCaveArea() {
        return caveArea;
    }

    private static final Map<Integer,SlayerMob> lookup = new HashMap<>();

    static {
        for(SlayerMob m : EnumSet.allOf(SlayerMob.class))
            lookup.put(m.getIndex(), m);
    }

    public static SlayerMob get(int index) {
        return lookup.get(index);
    }
}
