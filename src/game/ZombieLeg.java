package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Class representing a Zombie arm that can be used as a weapon once
 * dropped to the ground.
 */

public class ZombieLeg extends ZombieLimb{
	
	private CraftingAction craft = new CraftingAction(this);
	
	/**
	 * Constructor
	 * Creates a Zombie Leg
	 * 
	 * @param name - name of the limb.
	 * @param displayChar - Display character of limb in map
	 * @param damage - Damage this limb will cause when used as a weapon
	 * @param verb - Description of the attack when using this weapon
	 */
	public ZombieLeg(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}

	/**
	 * Reduces the Leg count of the zombie. 
	 */
	public void reduceLimbCount(Zombie zombie) {
		zombie.reduceLegCount();
		
	}

	/**
	 * Check if this limb is an arm.
	 * 
	 * @return false as this limb is NOT an arm.
	 */
	public boolean checkReduceLimbIsArm() {
		return false;
	}
	
		

	public void tick(Location currentlocaiton, Actor actor) {
		Boolean bool=false;
		for(Action action:this.allowableActions) {
			if(action.equals(craft)) {
				bool = true;
			}
			
		}
		
		if(bool == false) {
			this.allowableActions.add(craft);
		}
		
	}
	
	public void tick(Location location) {
		Boolean bool=false;
		for(Action action:this.allowableActions) {
			if(action.equals(craft)) {
				bool = true;
			}
			
			
		}
		if(bool == true) {
			this.allowableActions.remove(craft);
		}
		
	}
	
}
