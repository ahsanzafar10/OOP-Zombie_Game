package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring. It needs to be made more interesting.
 * 
 * @author ram.
 *
 */
public class Zombie extends ZombieActor {
	
	private int noOfArms = 2;
	private int noOfLegs = 2;
	private ArrayList<ZombieLimb> zombieLimbs = new ArrayList<ZombieLimb>(); // Collection to hold limbs
	private GameMap currentMap;	// Variable to store the current map that zombie is on
	private Random chance = new Random();
	private Behaviour[] behaviours = { new PickUpWeaponBehaviour(), new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10), new WanderBehaviour() };

	/**
	 * Constructor. 
	 * Adds the Zombie limbs as part of the creation of a zombie object.
	 * 
	 * @param name - The name of the zombie.
	 */
	
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		zombieLimbs.add(new ZombieArm("zombieLeftArm", 'A', 15, "STRIKES"));
		zombieLimbs.add(new ZombieArm("zombieRightArm", 'A', 15, "STRIKES"));
		zombieLimbs.add(new ZombieLeg("zombieRightLeg", 'L', 15, "STRIKES"));
		zombieLimbs.add(new ZombieLeg("zombieLeftLeg", 'L', 15, "STRIKES"));
	}
	
	private void shoutBrains(Display display) {
		/*
		 * Method to implement a 10% chance for a zombie shout "Brains"
		 */
		int chanceNum = 1 + chance.nextInt(10);
		if (chanceNum == 1) {
			display.println("-----------------BRAIIIINNNSSSS----------------");
		}
	}
	
	private void dropAllWeapons(int chanceNum, Location AttackerDestination) {
		
		// Method drops all the weapons a zombie if situation is right.
		
		// Collections to store the weapons that need to be dropped
		ArrayList<Item> weaponToRemove = new ArrayList<Item>(); 
		
		// If zombie loses one arm, then 50% chance of dropping weapons
		// If zombie loses both arms, then 100% chance of dropping weapons
		if ((this.hasOneArm() && (chanceNum < 50)) || this.hasZeroArm()) {							
			for (Item item : this.inventory) {
				if (item.asWeapon() != null) {
					weaponToRemove.add(item);
				}
			}
			for (Item weaponItem : weaponToRemove) {	
				currentMap.at(AttackerDestination.x(), AttackerDestination.y()).addItem(weaponItem);
				this.removeItemFromInventory(weaponItem);	
			}	 
		}
	}
	
	private boolean dropLimb(Location AttackerDestination) {
		/*
		 * Method to drop a limb of a zombie to the map.
		 * Returns True if an arm was dropped, else returns False. 
		 */
		
		Collections.shuffle(zombieLimbs); 
		ZombieLimb limbToDrop = zombieLimbs.get(0);
		zombieLimbs.remove(0);
		 
		currentMap.at(AttackerDestination.x(), AttackerDestination.y()).addItem(limbToDrop);
		 
		limbToDrop.reduceLimbCount(this);
		
		return limbToDrop.checkReduceLimbIsArm();
	}

	private int getStartIndex() {
		/*
		 * Method to obtain start Index.
		 * If a zombie has no arms, it should not be able to pick up any items.
		 * Return the appropriate endIndex so that the playTurn method can use it.
		 */
		
		int startIndex;
		
		if (this.hasZeroArm()) {
			startIndex = 1;
			return startIndex;
		}
		
		startIndex = 0;
		return startIndex;
	}

	private int getEndIndex(Action lastAction) {
		/*
		 * Method to obtain end Index.
		 * Example, If a zombie loses both legs, it should not be able to move.
		 * Return the appropriate startIndex so that the playTurn method can use it.
		 */
		
		int endIndex;
		
		if (noOfLegs < 2) {
			if ((lastAction instanceof MoveActorAction && this.hasOneLeg()) || this.hasZeroLeg()) {
				endIndex = 1;
				return endIndex;
			}
		}
		endIndex = this.behaviours.length - 1;
		return endIndex;		
	}
	
	/**
	 * Get the location of any actor on the map that is adjacent to this zombie.
	 * This method is only called when a zombie is attacked, so there is bound to be at least the player adjacent to zombie.
	 
	 * @param currentHere - Location of the zombie on the map.
	 * @return Location of adjacent actor.
	 */

	public Location getHurtingActorExit(Location currentHere) {

		// Loop around all the exits 
		// If exit contains an actor or dirt, then return it.
		for (Exit exit : currentHere.getExits()) {
			Location destination = exit.getDestination();

			if (destination.getGround().getDisplayChar() == '.' || destination.getActor() != null) {
				return destination;
			}
		}
		return null;
	}

	@Override
	/**
	 * Do some damage to zombie.
	 * Zombie has a 25% chance of dropping a limb (when attacked). (The limb may be used as a weapon once it falls of).
	 * If zombie loses an arm, there is a 50% chance of dropping any items it was holding.
	 * If zombie loses both arms, it drops any items it was holding.
	 * 
	 * @param points - number of hitpoints to deduct.
	 */
	public void hurt(int points) {
		 
		hitPoints -= points;
		boolean ArmReducedInthisAttack = false;
		 
		int chanceNum = 1+chance.nextInt(100);
		Location AttackerDestination = this.getHurtingActorExit(currentMap.locationOf(this));
		
		
		// If chance passes, then reduce a limb
		if (chanceNum < 25 & (zombieLimbs.size() > 0)) {
			// The 'dropLimb()' method drops a limb.
			// And returns true if zombie lost an arm during this attack, else returns false.
			ArmReducedInthisAttack = this.dropLimb(AttackerDestination);
		}
		
		// If an arm was lost in this attack, we then need to drop all weapons according to the
		// situation (did zombie lose one arm, or did zombie lose both arms).
		if (ArmReducedInthisAttack) {																				
			this.dropAllWeapons(chanceNum, AttackerDestination);
		}
	}
	
	
	
	@Override
	/**
	 * Returns an Intrinsic weapon; either a punch or bite.
	 * If zombie has both arms, then 50/50 chance of returning either a punch or bite.
	 * If zombie has one arm, then 25% chance of punching, 75% chance of biting.
	 * If zombie has 0 arms, then 0% chance of punching, 75% chance of biting.
	 * 
	 * @return An Intrinsic Weapon
	 */
	public IntrinsicWeapon getIntrinsicWeapon() {
		
		int chanceNum = 1 + chance.nextInt(100);
		int punchProbability = noOfArms * 25;
		
		if (chanceNum < punchProbability) {
			return new IntrinsicWeapon(10, "punches");
		}

		else {
			return new IntrinsicWeapon(15, "bites");
		}
		
	}
	
	/**
	 * If a Zombie can pick up a weapon it will. If not, it will attack if possible. If not, it will chase any human within
	 * 10 spaces. If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * 
	 * @param lastAction previous Action, if it was a multiturn action
	 * 
	 * @param map the map where the current Zombie is
	 * 
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		/*
		 * We change the startIndex and endIndex depending on the condition of the zombie.
		 * For example, if a zombie has no legs, then we set the indexes such that the zombie is not allowed 
		 * to use Hunt or Wander Behaviour.
		 * 
		 * If a zombie has no arms, then the zombie is not allowed to use the PickUpItem Behaviour.
		 */

		// Store the current map as a field (so that we can drop limbs to the map when a zombie is attacked).
		this.currentMap = map;
		
		// Set the indexes
		int startIndex =  this.getStartIndex();
		int endIndex = this.getEndIndex(lastAction);
		
		// Shout "Brains" with a 10% chance
		this.shoutBrains(display);
		
		// Loop over behaviours
		for (int i=startIndex; i <= endIndex; i++) {
			Behaviour behaviour = behaviours[i];
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		
		return new DoNothingAction();
	}

	
	/**
	 * Reduces the arm count of the zombie
	 * 
	 */
	public void reduceArmCount() {
		this.noOfArms -= 1;
	}

	/**
	 * Reduces the leg count of the zombie
	 */
	public void reduceLegCount() {
		this.noOfLegs -= 1;
	}

	/**
	 * Check if a zombie has one arm
	 * 
	 * @return True if a zombie has one arm
	 */
	public boolean hasOneArm() {
		return this.noOfArms == 1;
	}

	/**
	 * Check if a zombie has zero arms
	 * 
	 * @return True if a zombie has zero arms
	 */
	public boolean hasZeroArm() {
		return this.noOfArms == 0;
	}
	
	/**
	 * Check if a zombie has one leg
	 * 
	 * @return True if a zombie has one leg
	 */
	public boolean hasOneLeg() {
		return this.noOfLegs == 1;
	}
	
	/**
	 * Check if a zombie has zero legs
	 * 
	 * @return True if a zombie has zero legs
	 */
	public boolean hasZeroLeg() {
		return this.noOfLegs == 0;
	}
	
}
