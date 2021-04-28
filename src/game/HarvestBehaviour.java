package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


/**
 * A class that generates a FarmerHarvestAction if the current Actor is standing
 * next to a dirt.
 * 
 * @author HinSeng Leong
 *
 */
public class HarvestBehaviour implements Behaviour {
	
	private Random random = new Random();
	
	
	/**
	 * Has 33% probability to return a FarmerHarvestAction that harvest a crop for food.
	 * 
	 */
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getDisplayChar()=='O') {
            		actions.add(new FarmerHarvestAction(destination));
            }
        }
		
		if (map.locationOf(actor).getDisplayChar()=='O') {
        		actions.add(new FarmerHarvestAction(map.locationOf(actor)));
        }
		
		if (!actions.isEmpty()) {
			if (random.nextInt(3) == 2) {
				return actions.get(random.nextInt(actions.size()));
				} else {
				  return null;
				}
		}
		return null;
	}
}
