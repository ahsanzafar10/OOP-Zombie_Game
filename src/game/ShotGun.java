package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * A shot gun.
 * 
 * @author Ahsan Zafar
 */


public class ShotGun extends Item{
	
	ShotGunAction[] firingActions = {new ShotGunFiringNorthAction(), new ShotGunFiringSouthAction(),
									 new ShutGunFiringEastAction(), new ShotGunFiringWestAction()};

	/**
	 * Constructor 
	 * Does not take in any arguments.
	 */
	public ShotGun() {
		super("ShotGun", 'S', true);
		this.addCapability(GunCapability.SHOTGUN);
	}
	
	/**
	 * Check if Actor has ammunition in it's inventory.
	 * 
	 * @param actor - The actor whose turn is currently being played.
	 * @return True if actor has ammo, else False.
	 */
	public boolean ActorHasAmmo(Actor actor) {
		
		
		for (Item item: actor.getInventory()) {
			if (item.hasCapability(GunCapability.SHOTGUN)) {
				return true;
			}
		}
	
		return false;
	}
	
	/**
	 * Removes capibility to shoot from shotgun by removing all actions.
	 */
	public void removeFiringActions() {
		
		for (int i=0; i < firingActions.length; i++) {
			ShotGunAction shotgunAction = firingActions[i];
			this.allowableActions.remove(shotgunAction);
		}
	}
		
	/**
	 * Adds capibility to shoot from shotgun by adding all actions.
	 */
	public void addFiringActions() {
		for (int i=0; i < firingActions.length; i++) {
			ShotGunAction shotgunAction = firingActions[i];
			this.allowableActions.add(shotgunAction);
		}
	}
	
	@Override
	/**
	 * This method is called once per turn by engine if actor is holding a shotgun.
	 * Adds shooting capibility to shot gun only if the actor has ammunition.
	 */
	public void tick(Location currentlocation, Actor actor) {
		
		// Once Ammo has been used, we remove capability to shoot action.
		removeFiringActions();
		
		// A shotgun only has capibility to shoot if player has ammo.
		if (ActorHasAmmo(actor)) {
			addFiringActions();
		}
		
	}
	
	/**
	 * This method is called once per turn by engine if shotgun is lying on the ground.
	 * Removes the ability to fire from a ShotGun object, since it is lying on the ground
	 */
	@Override
	public void tick(Location location) {
		removeFiringActions();
	}
}
