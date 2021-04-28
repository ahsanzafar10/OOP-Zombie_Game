package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

/** 
 * Class representing Zombie leg that can be used as a weapon once
 * dropped to the ground.
 */

public class ZombieArm extends ZombieLimb {
	
	protected int test = 0;
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
	public ZombieArm(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}
	
	/**
	 * Reduces the arm count of the zombie. 
	 */
	public void reduceLimbCount(Zombie zombie) {
		zombie.reduceArmCount();
	}

	/**
	 * Check if this limb is an arm.
	 * 
	 * @return True as this limb is an arm.
	 */
	public boolean checkReduceLimbIsArm() {
		return true;
	}
	
	
	
	@Override
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
