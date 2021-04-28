package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;


/**
 * A class that generates a EatAction if the current Actor is carrying
 * food.
 * 
 * @author HinSeng Leong
 *
 */
public class EatingBehaviour implements Behaviour {
	
	/**
	 * Return a EatingAction if an actor is hurt.
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {

		Human human = (Human) actor;
		if (human.getCurrentHitPoints() < human.getMaxHitPoints())
		{
			for (Item item : actor.getInventory() ) {
				if (item.toString() == "Food")
				{
					return new EatingAction(item);
				}
			}
		}
	return null;
	}
}
