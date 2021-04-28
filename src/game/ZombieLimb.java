package game;

import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;


/**
 * Base Class representing Zombie limbs that can be used as a weapon once
 * dropped to the ground.
 * 
 * 
 * @author Ahsan Zafar
 */

public abstract class ZombieLimb extends WeaponItem {
	
	private CraftingAction craft = new CraftingAction(this);
	
	/**
	 * Constructor
	 * Creates a Zombie arm
	 * 
	 * @param name - name of the limb.
	 * @param displayChar - Display character of limb in map
	 * @param damage - Damage this limb will cause when used as a weapon
	 * @param verb - Description of the attack when using this weapon
	 */
	public ZombieLimb(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}
	
	
	public abstract void reduceLimbCount(Zombie zombie);
	
	public abstract boolean checkReduceLimbIsArm();
	
	public boolean tickHelper() {
		boolean bool=false;
		for(Action action:this.allowableActions) {
			if(action.equals(craft)) {
				bool = true;
			}
		}
		
		return bool;
	}
	
	
	
	/**
	 * Check whether the item is being carried.
	 * If it is being carried, add CraftingAction to allowableActions
	 * @param currentLocation The location of the actor carrying this Item.
	 * @param actor The actor carrying this item.
	 * 
	 */

	@Override
	public void tick(Location currentLocation, Actor actor) {
		boolean bool = tickHelper();
		
		if(!bool) {
			this.allowableActions.add(craft);
		}
			
	}
	
	
	/**
	 * Check whether the item is on the ground.
	 * If it is not being carried, remove CraftingAction from allowableActions
	 * @param location The location of this item.
	 */

	@Override
	public void tick(Location location) {
		boolean bool = tickHelper();
		
		if(bool) {
			this.allowableActions.remove(craft);
		}
		
	}

	
	

}
