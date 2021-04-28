package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


/**
 * Action for human to eat food to be healed.
 * 
 * @author HinSeng Leong
 */
public class EatingAction extends Action {
	
	/**
	 * The item needs to be eaten.
	 */
	protected Item item;
	
	/**
	 * Constructor.
	 * 
	 * @param item the item to be eaten
	 */
	public EatingAction(Item item) {
		this.item = item;
	}

	
	/**
	 * Executes a eating and heals for 10hp.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.heal(10);
		actor.removeItemFromInventory(item);
		return actor + " eats the food and heals for 10hp!";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats the food";
	}
}
