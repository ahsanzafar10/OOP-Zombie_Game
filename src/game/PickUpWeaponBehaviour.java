package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;


/**
 * A class that generates a PickUpItemAction if the current Actor is standing 
 * on a weapon that can be picked up.
 */

public class PickUpWeaponBehaviour implements Behaviour {

	@Override	
	/**
	 * If a zombie is standing on a weapon when its turn starts, 
	 * it should pick it up.
	 * 
	 * @return A pickUpItemAction if a zombie is able to pick up a 
	 * weapon. Else, returns null.
	 */
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		
		// Are the current items on this location weapons ?
		// If yes, then zombie should pick it up.
		for (Item item : here.getItems()) {
			if (!(item.asWeapon() instanceof WeaponItem)	) {
				continue;
				
			}
		
			return new PickUpItemAction(item);
		}
	return null;
	}
}


