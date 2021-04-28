package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	private int hitChance = 50;
	private boolean restoreHealth = false;
	
	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}
	

	@Override
	/**
	 * Executes an attack on target.
	 */
	public String execute(Actor actor, GameMap map) {
		 

		Weapon weapon = actor.getWeapon();
		
		// The following if block allows us to check if the attacking actor is 
		// a zombie and if the weapon to use is a bite.
		// If that is the case, then we need to lower the chance of a hit and 
		// restore the health of the zombie as the spec mentions.
		if (weapon.verb() == "bite") {
			hitChance = 40;
			restoreHealth = true;
		}
		
		Random chance = new Random();
		int chanceNum = 1+chance.nextInt(100);
		
		if (chanceNum > hitChance) {
			return actor + " misses " + target + ".";
		}
		
 
		if (restoreHealth) {
			actor.heal(5);
		}
			
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (TargetIsPlayer(target)) {
			((Player) target).WaitingRounds=0;
		}
		
		if (!target.isConscious()) {
			
			processKill(map, target);
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}
	
	public void processKill(GameMap map, Actor target) {
		Item corpse;
		
		// If human is dead, it needs rise from dead, so we create a resurrectable corpse item.
		// Else, we create a normal corpse item.
		if (target.hasCapability(ZombieCapability.ALIVE)) {
			corpse = new ResurrectableCorpseItem("Dead " + target, '^');		
		} else {
			corpse = new PortableItem("dead " + target, '%');
		}
		
		map.locationOf(target).addItem(corpse);
		
		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)		
			drop.execute(target, map);
		map.removeActor(target);	
	}
	
	public boolean TargetIsPlayer(Actor target) {
		if (target instanceof Player) {
			return true;
		}
		return false;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
