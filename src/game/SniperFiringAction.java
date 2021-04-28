package game;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class SniperFiringAction extends AttackAction{
	private HashMap<Integer, int[]> DamageNChance = new HashMap<Integer, int[]>();
	private Random chance = new Random();
	private ZombieCapability attackableTeam = ZombieCapability.UNDEAD;
	
	/**
	 * Constructor
	 * Does not take in any arguments.
	 */
	public SniperFiringAction() {
		super(null);
		this.initializeDamageNChance();
	}
	
	
	/**
	 * Obtain all the zombies on the map
	 * 
	 * @param map - The current map.
	 * @return - An ArrayList consisting of coodinates where zombies are present.
	 */
	public ArrayList<Actor> getAllZombies(GameMap map) {
		int xRange = map.getXRange().max();
		int yRange = map.getYRange().max();
		
		ArrayList<Actor> zombies = new ArrayList<Actor>();
		
		for (int x=0; x<=xRange; x++) {
			for (int y=0; y<=yRange; y++) {
				Location location = map.at(x, y);
				
				if (location.containsAnActor() &&  location.getActor().hasCapability(attackableTeam)) {
					zombies.add(location.getActor());
				}
			}
		}
		
		return zombies;
	}
	
	/**
	 * Check to see if this is the start of a new Sniper Round.
	 * 
	 * @param WaitingRounds - The number of turns spent aiming
	 * @return True if 0 rounds spend aiming, else False.
	 */
	public boolean StartOfNewSniperRound(int WaitingRounds) {
		return WaitingRounds == 0;
	}
	
	public void SetZombieTarget(GameMap map) {
		ArrayList<Actor> zombies = getAllZombies(map);
		
		String promptZombieTarget = "";
		
		for (int i=0; i<zombies.size(); i++) {
			promptZombieTarget += i + ". " + zombies.get(i).toString() + "\n";
		}
		
		// Getting a zombie target
		Display display = new Display();
		display.println(promptZombieTarget);
		char option = display.readChar();
		target = zombies.get((int)option - 48);
	}
	
	/**
	 * Display prompt to console so that user can shoose either to aim or shoot.
	 * 
	 * @return 1 if shooting, else 2.
	 */
	public int promptShootOrAim() {
		String AimingPrompt = "1. Shoot straight away \n2. Spend a round aiming";
		
		Display display = new Display();
		display.println(AimingPrompt);
		char option1 = display.readChar();
		
		return (int)option1 - 48;
	}
	
	
	/**
	 * Executes a Sniper shooting attack.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		// Obtain number of turns spent aiming.
		int WaitingRounds = ((Player) actor).WaitingRounds;
		
		// If start of a new sniper round, we need to display options to choose target.
		if (StartOfNewSniperRound(WaitingRounds)) {
			SetZombieTarget(map);
		}
		
		// Ask user whether to shoot or aim.
		int OptionChosen = promptShootOrAim();
		
		// If shooting, then execute shot  
		String misOrhit = "misses"; 		// Pre-mature initialisation of 'misOrhit'.
		if (OptionChosen==1) {
			RemoveAmmoFromInventory(actor);
			ResetWaitingRounds(actor);
			int damage = DamageNChance.get(getWaitingRoundsIndex(WaitingRounds))[0];
			int hitChance = DamageNChance.get(getWaitingRoundsIndex(WaitingRounds))[1];
			int chanceNum = 1 + chance.nextInt(100);
			
			if (chanceNum < hitChance) {
				
				misOrhit = "hits";
				target.hurt(damage);
				String result = actor + " " + "shoots " + " " + target + " with Sniper for " + damage + " damage.";
				if (!target.isConscious()) {
					this.processKill(map, target);
					result += System.lineSeparator() + target + " is killed.";
					return result;
				}
			}
		}
		
		// if Aiming, then do not execute shot.
		if (OptionChosen == 2) {
			incrementWaitingRounds(actor);
			return "Player chooses to spend round aiming";
		}
		
		switch (misOrhit) {
			case "misses":
				return "Player chooses to shoot but misses.";
			default:
				int damage = DamageNChance.get(getWaitingRoundsIndex(WaitingRounds))[0];
				return "Player shoots" + " " + target + " with Sniper for " + damage + " damage.";
		}
	}
	

	/**
	 * Menu description to display to console
	 * 
	 * @return A string describing action to choose.
	 */		
	@Override
	public String menuDescription(Actor actor) {
		
		return actor + " uses Sniper to aim/shoot. ";
	}
	
	/*
	 * Private method to intialise the damages and and chance depending on waiting rounds.
	 * 
	 * For example: 
	 * 		- If player decides to shoot immediately, then damage level is 30 and hitChance is 75%.
	 * 		- If player decides to aim 1 round then shoot, then damage level is 60 and hitChance is 90%. 
	 */
	private void initializeDamageNChance() {
		int[] Round0 =  {30,75};
		DamageNChance.put(0, Round0);
		
		int[] Round1 =  {60, 90};
		DamageNChance.put(1, Round1);
		
		int[] Round2 =  {100, 100};
		DamageNChance.put(2, Round2);
		
	}
	
	/*
	 * Private method to obtain the index to use when accessing damage&Hitchance.
	 * 		- Index will be 0 if player chooses to shoot without aiming.
	 * 		- Index will be 1 if player chooses to shoot with aiming for 1 round.
	 * 		- Index will be 2 if player chooses to shoot with aiming for 2 or more rounds.
	 */
	private int getWaitingRoundsIndex(int WaitingRounds) {
		if (WaitingRounds >= 2) {
			return 2;
		}
		
		return WaitingRounds;
	}
	
	/*
	 * Remove Sniper ammunition from actors inventory.
	 */
	private void RemoveAmmoFromInventory(Actor actor) {
		Item itemToremove = null;
		for (Item item: actor.getInventory()) {
			if (item instanceof SniperAmmo) {
				itemToremove = item;
			}
		}
		actor.removeItemFromInventory(itemToremove);
	}
	
	/*
	 * Increment the number of rounds spent aiming.
	 */
	private void incrementWaitingRounds(Actor actor) {
		((Player) actor).WaitingRounds += 1;
	}
	
	/*
	 * Reset the number of rounds spent aiming.
	 */
	private void ResetWaitingRounds(Actor actor) {
		((Player) actor).WaitingRounds = 0;
	}
	
}
