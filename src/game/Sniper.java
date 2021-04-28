package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * A sniper
 * 
 * @author Ahsan Zafar
 *
 */
public class Sniper extends Item{
	
	
	
	SniperFiringAction FiringAction = new SniperFiringAction();
	
	/**
	 * Constructor
	 * Does not take in any arguments
	 */
	public Sniper() {
		super("Sniper", 'N', true);
		this.addCapability(GunCapability.SNIPER);
	}
	
	public boolean ActorHasAmmo(Actor actor) {
		for (Item item: actor.getInventory()) {
			if (item.hasCapability(GunCapability.SNIPER)) {
				return true;
			}
		}
	
		return false;
	}
	
	/**
	 * This method is called once per turn by the engine.
	 * Adds shooting capibility to Sniper only if the actor has ammunition.
	 */
	@Override
	public void tick(Location currentlocation, Actor actor) {
		// Once Ammo has been used, we remove capability to shoot action.
		this.allowableActions.remove(FiringAction);
		
		// A shotgun only has capibility to shoot if player has ammo.
		if (ActorHasAmmo(actor)) {
			this.allowableActions.add(FiringAction);
		}
	}
	
	/**
	 * This method is called once per turn by the engine if an object of this class is on the ground.
	 * Removes the ability to fire from a Sniper object, since it is lying on the ground
	 */
	public void tick(Location location) {
		this.allowableActions.remove(FiringAction);
	}
}

