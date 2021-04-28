package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * Food that can be eaten.
 * 
 * @author HinSeng Leong
 *
 */

public class Food extends PortableItem {

	private EatingAction eat = new EatingAction(this);
	
	public Food(String name, char displayChar) {
		super(name, displayChar);
	}
	
	public boolean tickHelper() {
		boolean bool=false;
		for(Action action:this.allowableActions) {
			if(action.equals(eat)) {
				bool = true;
			}
		}
		
		return bool;
	}
	
	
	/**
	 * Check whether the item is being carried.
	 * If it is being carried, add EattingAction to allowableActions
	 * @param currentLocation The location of the actor carrying this Item.
	 * @param actor The actor carrying this item.
	 * 
	 */
	public void tick(Location currentLocation, Actor actor) {
		boolean bool = tickHelper();

		if(!bool) {
			this.allowableActions.add(eat);
		}
		
		
	}
	
	/**
	 * Check whether the item is on the ground.
	 * If it is not being carried, remove EatingAction from allowableActions
	 * @param location The location of this item.
	 */
	public void tick(Location location) {
		boolean bool = tickHelper();
		
		if(bool) {
			this.allowableActions.remove(eat);
		}
		
	}
	
	
	

}
