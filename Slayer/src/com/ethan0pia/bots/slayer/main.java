package com.ethan0pia.bots.slayer;
import com.ethan0pia.bots.slayer.branches.hasTaskBranch;

import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;

import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.runemate.game.api.hybrid.local.Varbits;

import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;//loadstones

import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.location.navigation.Traversal;

/*

Package/Function Notes
 =======================================
|           Dev Docs                    |
 =======================================
https://www.runemate.com/developer/jdocs/


//import com.runemate.game.api.rs3.net//search beastiary by name, id, or area


//import com.runemate.game.api.hybrid.entities.status//getPercent() enemy adren



//import com.runemate.game.api.rs3.entities.RS3Actor

    //Actor -- com.runemate.game.api.rs3.entities.RS3Actor
        //getHealthGauge()
    //enemy
        //getPercent() enemy adren



//"Slayer counter"
//varp index 7917 is slayer counter
//varp index 7923 or 7219 is slayer task
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


//root
import com.ethan0pia.bots.slayer.branches.hasTaskBranch;

public class main extends TreeBot {

	@Override
	public TreeTask createRootTask() {
		return new hasTaskBranch();
	}
}



/*
//global slayer master to int variables
//enum master{Turael, Spria, Mazchna, Achtryn, Vannaka, Chaeldar, Sumona, Duradel, Lapalok, Kuradal, Morvran}
//Turael/Spria, Mazchna/Achtryn, Vannaka, Chaeldar, Sumona, Duradel/Lapalok, Kuradal, Morvran

//global list of mobs we don't support in script

public abstract class main extends Treebot implements {
//global override for combat type if able. Makes any combat type usable even if weak.
    public int combatTypeOverride = 0;//1=melee, 2=ranged, 3=magic(air spells), 0=default
    //global slayer master to int variables

    //Turael/Spria, Mazchna/Achtryn, Vannaka, Chaeldar, Sumona, Duradel/Lapalok, Kuradal, Morvran

    public void main(String[] args) {
        //while loop
        while (true) {
            //determine which master should be used based on slayer level, combat lvl, or UI
            int master = 1;
            
            //checks if current task
            int currentCount = Varbits.load(7917).getValue();

            //if no current task
            //walk to slayer master
            //gets new task if no current task
            if (currentCount == 0) {
                walkToSlayerMaster(master);
            }

            //checks what task is
            int mob = Varbits.load(7923).getValue();
            //calls correct mob function
            switch (mob) {
                //case "Aberrant Spectres"		aberrantSpectres(); break;
                //case "Abyssal Demons"			abyssalDemons(); break;
                //case "Acheron Mammoth"		acheronMammoth(); break;
                //case "Adamant Dragons"		adamantDragons(); break;
                //case "Airuts"					airuts(); break;
                //case "Ankous"					ankous(); break;
                //case "Aquanites"				aquanites(); break;
                //case "Ascension Members"		ascentionMembers(); break;
                //case "Automations"			automations(); break;
                //case "Aviansies"				aviansies(); break;
                /////case "banshees":           banshees(); break;
                /////case "bats":               bats(); break;
                //case "Basilisks"				basilisks(); break;
                case 13:                        bears(); break;
                case 5:                         birds(); break;
                //case "Black Demons"			blackDemons(); break;
                //case "Black Dragons"			blackDragons(); break;
                //case "Bloodvelds"				bloodvelds(); break;
                //case "Blue Dragons"			blueDragons(); break;
                //case "Brine Rats"				brineRats(); break;
                //case "Bronze Dragons"			bronzeDragons(); break;
                //case "Camel Warriors"			camelWarriors(); break;
                //case "Catablepons"			catablepons(); break;
                /////case "caveBugs":           caveBugs(); break;
                //case "Cave Crawlers"			caveCrawlers(); break;
                //case "Cave Horrors"			caveHorrors(); break;
                /////case "Cave Slimes":        caveSlimes(); break;
                //case "Celestial Dragons"		celestialDragons(); break;
                //case "Chaos Giants"			chaosGiants(); break;
                //case "Cockatrices"			cockatrices(); break;
                //case "Corrupted Creatures"	corruptedCreatures(); break;
                case 6:                         cows(); break;
                case 39:                        crawlingHands(); break;
                //case "Crocodiles"				crocodiles(); break;
                //case "Crystal Shapeshifters"	crystalShapeshifters(); break;
                //case "Cyclopses"				cyclopses(); break;
                //case "Dagannoths"				dagannoths(); break;
                //case "Dark Beasts"			darkBeasts(); break;
                /////case "Desert Lizards":     desertLizards(); break;
                //case "Desert Strykewyrms"		desertStrykewyrms(); break;
                /////case "Dogs":               dogs(); break;
                //case "Dust Devils"			dustDevils(); break;
                /////case "Dwarves":            dwarves(); break;
                //case "Earth Warriors"			earthWarriors(); break;
                //case "Edimmus"				edimmus(); break;
                //case "Elves"					elves(); break;
                //case "Fever Spiders"			feverSpiders(); break;
                //case "Fire Giants"			fireGiants(); break;
                //case "Flesh Crawlers"			fleshCrawlers(); break;
                //case "Fungal Mages"			fungalMages(); break;
                //case "Ganodermic Creatures"	ganodermicCreatures(); break;
                //case "Gargoyles"				gargoyles(); break;
                case 111:                       gelatinousAbom(); break;
                //case "Gemstone Dragons"		gemstoneDragons(); break;
                /////case "Ghosts":             ghosts(); break;
                //case "Glacors"				Glacors(); break;
                //case "Ghouls"					ghouls(); break;
                case 2:                         goblins(); break;
                //case "Goraks"					goraks(); break;
                //case "Greater Demons"			greaterDemons(); break;
                //case "Green Dragons"			greenDragons(); break;
                //case "Grifolapines"			grifolapines(); break;
                //case "Grifolaroos"			grifolaroos(); break;
                /////case "Grotworms":          grotworms(); break;
                //case "Harpy Bug Swarms"		harpyBugSwarms(); break;
                //case "Hellhounds"				hellhounds(); break;
                //case "Hill Giants"			hillGiants(); break;
                //case "Hobgoblins"				hobgoblins(); break;
                //case "Ice Giants"				iceGiants(); break;
                //case "Ice Strykewyrms"		iceStrykewyrms(); break;
                //case "Ice Warriors"			iceWarriors(); break;
                /////case "Icefiends":          icefiends(); break;
                //case "Infernal Mages"			infernalMages(); break;
                //case "Iron Dragons"			ironDragons(); break;
                //case "Jellies"				jellies(); break;
                //case "Jungle Horrors"			jungleHorrors(); break;
                //case "Jungle Strykewyrms"		jungleStrykewyrms(); break;
                //case "Kal'gerion Demons"		kalgerionDemons(); break;
                //case "Kalphites"				kalphites(); break;
                //case "Killerwatts"			killerwatts(); break;
                //case "Kurasks"				kurasks(); break;
                //case "Lava Strykewyrms"		lavaStrykewyrms(); break;
                //case "Lesser Demons"			lesserDemons(); break;
                //case "Living Rock Creatures"	livingRockCreatures(); break;
                //case "Living Wyverns"			livingWyverns(); break;
                /////case "Minotaurs":          minotaurs(); break;
                //case "Mithril Dragons"		mithrilDragons(); break;
                //case "Mogres"					Mogres(); break;
                //case "Molanisks"				molanisks(); break;
                /////case "Monkeys":            monkeys(); break;
                //case "Moss Giants"			mossGiants(); break;
                //case "Muspahs"				muspahs(); break;
                //case "Mutated Jadinkos"		mutatedJadinkos(); break;
                //case "Mutated Zygomites"		mutatedZygomites(); break;
                //case "Nechryaels"				nechryaels(); break;
                //case "Nightmares"				nightmares(); break;
                //case "Nihils"					nihils(); break;
                //case "Ogres"					ogres(); break;
                //case "Otherworldly Beings"	otherworldlyBeings(); break;
                /////case "Pigs":               pigs(); break;
                //case "Pyrefiends"				pyrefiends(); break;
                //case "Red Dragons"			redDragons(); break;
                //case "Ripper Demons"			ripperDemons(); break;
                //case "Rock Slugs"				rockSlugs(); break;
                //case "Rune Dragons"			runeDragons(); break;
                //case "Scabarites"				scabarites(); break;
                //case "Scabarite Minions"		scabariteMinions(); break;
                case 7:                         scorpions(); break;
                //case "Sea Snakes"				seaSnakes(); break;
                //case "Shades"					shades(); break;
                //case "Shadow Creatures"		shadowCreatures(); break;
                //case "Shadow Warriors"		shadowWarriors(); break;
                //case "Skeletal Wyverns"		skeletalWyverns(); break;
                /////case "Skeletons":          skeletons(); break;
                //case "Soul Devourers"			soulDevourers(); break;
                case 4:                         spiders(); break;
                //case "Spiritual Mages"		spiritualMages(); break;
                //case "Spiritual Warriors"		spiritualWarriors(); break;
                //case "Steel Dragons"			steelDragons(); break;
                //case "Suqahs"					suqahs(); break;
                //case "Terror Dogs"			terrorDogs(); break;
                //case "Tormented Demons"		tormentedDemons(); break;
                case 18:                        trolls(); break;
                //case "Turoths"				turoths(); break;
                //case "TzHaar"					tzHaar(); break;
                //case "Vampyres"				vampyres(); break;
                //case "Vyrewatches"			vyrewatches(); break;
                //case "Wall Beasts"			wallBeasts(); break;
                //case "Warped Terrorbirds"		warpedTerrorbirds(); break;
                //case "Warped Tortoises"		warpedTortoises(); break;
                //case "Werewolves"				werewolves(); break;
                //case "Waterfiends"			waterfiends(); break;
                case 9:                         wolves(); break;
                case 10:                        zombies(); break;
                //default:						skip(); break;//Dont skip until we have all the mob numbers
            }

            //end of task, returns to slayer master and then loops back to getting new task


            //end while loop here
        }
        //return; - end the program
    }

    public void walkToSlayerMaster(int master) {
        switch (master) {
            case 1:
                Lodestone.TAVERLEY.teleport();
                Execution.delayWhile(() -> (Players.getLocal().isMoving() || Players.getLocal().getAnimationId() != -1)
                        && Players.getLocal().distanceTo(Lodestone.TAVERLEY.getPosition()) > 3);
                final Coordinate masterCoords = new Coordinate(2911, 3422, 0);
                final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(masterCoords);
                if (path != null) { //always nullcheck!
                    path.step();
                }

        }


    }

//perameters:mob name or ID
//gearUp function
//switches for groups of monsters:
//calls the correct monster function
//normal groups will be outfitted at 1 of 3 banks randomly chosen with the correct weapon, armor, and combat type. bar 1 is melee, 2 is range, 3 is magic
//calls walking function with mob name/ID and "normal" for combat type


//special Mobs switch for each special mob
//gears the user up with the correct special mob equipment
//set to correct combat bar
//may call special combat system if the monster has a slayer final blow requirement
//otherwise, calls walking function and "normal" combat system


//---------------------------------------------------------------monsters--------------------------------------------------------------------------//

//perameters:
//monster functions(one function for each monster)
//normal groups will be outfitted at 1 of 3 banks randomly chosen with the correct weapon, armor, and combat type. bar 1 is melee, 2 is range, 3 is magic
//abnormal mobs will be outfitted in the correct armor, weapon, or item to kill the enemy
//Each mob will have it's own walking instructions
//if the mob is a normal combat mob i.e. hit until dead, call normal fighting system with the mob's ID
//if the mob is abnormal, i.e. takes a killing blow or item used on them, call the special combat system with the mob's name

//look up mob with Beastiary.Beast.lookupByName("name") function

    //Master:Turael/Spria, Mazchna/Achtryn, Vannaka, Chaeldar, Sumona
//name:Banshee
//Aggressive:Yes
//Combat Weakness:Ranged
//location:Slayer Tower
//Special:Earmuffs(4166/4167/13277)
    public void banshees() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn
    //name:Bat
    //Aggressive:No
    //Combat Weakness:Ranged
    //location:Path to Paterdomus by odd old man
    //Special:
    public void bats() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn
    //name:Grizzly bear
    //Aggressive:Yes
    //Combat Weakness:Water Spells
    //location:Ardy Mine
    //Special:
    public void bears() {
        Lodestone.FALADOR.teleport();
        Execution.delayWhile(() -> (Players.getLocal().isMoving() || Players.getLocal().getAnimationId() != -1)
                && Players.getLocal().distanceTo(Lodestone.FALADOR.getPosition()) > 3);
        final Coordinate monster = new Coordinate(3194, 3187, 0);
        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(monster);
        if (path != null) { //always nullcheck!
            path.step();
        }

    }

    //Master:Turael/Spria
    //name:Chicken(bird)
    //Aggressive:No
    //Combat Weakness:Fire Spells
    //location:Several(find a member only obscure location)
    //Special:
    public void birds() {


    }

    //Master:Turael/Spria
    //name:Cave Bug
    //Aggressive:No
    //Combat Weakness:Melee
    //location:Taverly Slayer Dungeon
    //Special:
    public void caveBugs() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn
    //name:Cave slime
    //Aggressive:No
    //Combat Weakness:Melee
    //location:Lumbridge Swamp Caves
    //Special:anti-poison(175-186,2446-2449), light source
    public void caveSlimes() {


    }

    //Master:Turael/Spria
    //name:Cow
    //Aggressive:No
    //Combat Weakness:Earth Spells
    //location:Falador Farm
    //Special:
    public void cows() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn, Chaeldar
    //name:Crawling hand
    //Aggressive:No
    //Combat Weakness:Fire Spells
    //location:Taverley Slayer Dungeon
    //Special:
    public void crawlingHands() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn
    //name:Desert lizard
    //Aggressive:No
    //Combat Weakness:Melee
    //location:Desert
    //Special:Ice cooler (6696), Water Source
    public void desertLizards() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn
    //name:Jackal(dog)
    //Aggressive:Yes
    //Combat Weakness:Air Spells
    //location:Desert
    //Special:Water Source
    public void dogs() {


    }

    //Master:Turael/Spria
    //name:Dwarf
    //Aggressive:No
    //Combat Weakness:Air Spells
    //location:Dwarven Mine
    //Special:
    public void Dwarves() {


    }

    //Master:Turael/Spria
    //name:Gelatinous Abomination
    //Aggressive:No
    //Combat Weakness:Ranged
    //location:Taverley Slayer Dungeon
    //Special:spiked gauntlets(23035)
    public void gelatinousAbom() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn
    //name:Ghost
    //Aggressive:No
    //Combat Weakness:Ranged
    //location:Tavery Dungeon
    //Special:
    public void ghosts() {


    }

    //Master:Turael/Spria
    //name:Goblin
    //Aggressive:No
    //Combat Weakness:Air Spells
    //location:Port Sarim
    //Special:
    public void goblins() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn, Vannaka, Chaeldar
    //name:Young grotworm(grotworm)
    //Aggressive:No
    //Combat Weakness:Ranged
    //location:QBD Cave
    //Special:
    public void grotworms() {


    }

    //Master:Turael/Spria
    //name:Icefiend
    //Aggressive:No
    //Combat Weakness:Ranged
    //location:Ice Mountain
    //Special:Ice Gloves(1580)
    public void icefiends() {


    }

    //Master:Turael/Spria
    //name:Minotaur
    //Aggressive:No
    //Combat Weakness:Water Spells
    //location:Stronghold of Security
    //Special:
    public void minotaurs() {


    }

    //Master:Turael/Spria
    //name:Monkey
    //Aggressive:No
    //Combat Weakness:Earth Spells
    //location:Karamja
    //Special:
    public void monkeys() {


    }

    //Master:Turael/Spria
    //name:Pig
    //Aggressive:No
    //Combat Weakness:Earth Spells
    //location:Farm South of Fally
    //Special:
    public void pigs() {


    }

    //Master:Turael/Spria
//name:Scorpion
//Aggressive:No/Yes
//Combat Weakness:Melee
//location:Dwarven Mines
//Special:
    public void scorpions() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn
//name:Skeleton
//Aggressive:Yes
//Combat Weakness:Earth Spells
//location:Taverly Dungeon
//Special:
    public void skeletons() {


    }

    //Master:Turael/Spria
    //name:Giant spider
    //Aggressive:
    //Combat Weakness:Melee
    //location:Stronghold of Security
    //Special:
    public void spiders() {
        Lodestone.LUMBRIDGE.teleport();
        Execution.delayWhile(() -> (Players.getLocal().isMoving() || Players.getLocal().getAnimationId() != -1)
                && Players.getLocal().distanceTo(Lodestone.LUMBRIDGE.getPosition()) > 3);
        final Coordinate monster = new Coordinate(3194, 3187, 0);
        final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(monster);
        if (path != null) { //always nullcheck!
            path.step();
        }
    }

    //Master:Turael/Spria, Vannaka, Chaeldar, Sumona
    //name:Troll chucker, Troll brute, Troll shaman
    //Aggressive:No
    //Combat Weakness:Pick One
    //location:North of Burthrope
    //Special:
    public void trolls() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn
//name:Wolf
//Aggressive:Yes
//Combat Weakness:Water Spells
//location:White Wolf Mountain
//Special:
    public void wolves() {


    }

    //Master:Turael/Spria, Mazchna/Achtryn
//name:Zombie
//Aggressive:No
//Combat Weakness:Earth Magic
//location:Stronghold of Security
//Special:
    public void zombies() {


    }
*/

//--------------------------------------------------Banking/Gearing---------------------------------------------------------//


//will search bank for the highest usable combat gear of that type and set the combat bar to the correct bar
//perameters:
//equipMagic function(string spell type)

//equipMelee function

//equipRanged function


//--------------------------------------------------Fighting--------------------------------------------------------------//
/* Example fighter
import com.runemate.game.api.hybrid.Environment
import com.runemate.game.api.hybrid.region.Npcs
import com.runemate.game.api.hybrid.region.Players
import com.runemate.game.api.script.Execution
import com.runemate.game.api.script.framework.tree.LeafTask
import java.util.concurrent.TimeUnit

 * @author Septron
 * @since June 05, 2017
 *
class AttackChickenLeaf : LeafTask() {

    override fun execute() {
        val results = Npcs.newQuery().names("Chicken").actions("Attack").reachable().results()
        if (results.isNotEmpty()) {
            val npc = results.nearest()
            if (npc!!.interact("Attack")) {
                if (Execution.delayUntil({ !npc.isValid }, { Players.getLocal().isMoving },
                        500, TimeUnit.SECONDS.toMillis(10).toInt()
                )){
                    Environment.getLogger().info("We killed a chicken")
                } else {
                    Environment.getLogger().info("We failed to kill a chicken")
                }
            } else {
                Environment.getLogger().info("Failed to attack a chicken")
            }
        } else {
            Environment.getLogger().info("Failed to find a chicken")
        }
    }
}
*/


//perameters:monster name
//normalCombat function
    //fights the coresponding enemy with the ID/name of mob
    //when it runs out of food, finishes task, or dies, it returns to the monster function


//Parameters:monster name, item ID
//specialFightUseItem
    //fights the mob and uses the item on the mob when they are almost dead
    //when it runs out of food, special item, finishes task, or dies, it returns to the monster function

//Parameters:monster name
//specialFightKillingBlow
    //fights the mob and does killing blow
//}
