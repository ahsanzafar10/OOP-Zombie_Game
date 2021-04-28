package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * A class that generates a PickUpAction for human to pick up food
 * 
 * @author HinSeng Leong
 *
 */
public class HumanPickUpBehaviour implements Behaviour {

	private Random random = new Random();
	
	
	/**
	 * Has 50% probability to return a PickUpAction to pick up food.
	 * 
	 */
	@Override	
	public Action getAction(Actor actor, GameMap map) {
		
		Location here = map.locationOf(actor);
		
		for (Item item : here.getItems()) {
			if (item.toString() == "Food") {
				if (random.nextInt(2) != 0) {
					return new PickUpItemAction(item);
				}
			}
		}
		return null;
	}
}
