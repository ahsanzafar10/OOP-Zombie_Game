package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


/**
 * A class that generates a SowingAction if the current Actor is standing
 * next to a dirt.
 * 
 * @author HinSeng Leong
 *
 */

public class SowingBehaviour implements Behaviour {
	
	private Random random = new Random();
	
	
	/**
	 * Has 33% probability to return a SowingAction that sows on a random dirt near the actor.
	 * 
	 */

	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getDisplayChar()=='.') {
            	actions.add(new SowingAction(destination));
            }
        }
		
		if (!actions.isEmpty()) {
			if (random.nextInt(3) == 2) {
				return actions.get(random.nextInt(actions.size()));
			} else {
			  return null;
			}
		} else {
		  return null;
		}
	}
}
	

