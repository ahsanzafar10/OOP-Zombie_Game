package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Ground;


/**
 * A class that generates a FertilizeAction if the current Actor is standing on a dirt.
 * 
 * @author HinSeng Leong
 *
 */
public class FertilizeBehaviour implements Behaviour {

	private Random random = new Random();
	
	/**
	 * Has 50% probability to return a FertilizeAction that fertilizes a dirt.
	 * 
	 */
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		
		Location here = map.locationOf(actor);
		if (here.getGround().getDisplayChar() == 'o')
		{
			if (random.nextInt(2) != 0) {
				return new FertilizeAction(here);
			} else {
			    return null;
			}
		}
		
	return null;			
	}
}
