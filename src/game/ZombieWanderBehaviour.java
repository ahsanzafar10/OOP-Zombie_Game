package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class ZombieWanderBehaviour extends WanderBehaviour{
	/*
	 Override getAction
	 */
	
//	@Override
//	public Action getAction(Actor actor, GameMap map) {
//		ArrayList<Action> actions = new ArrayList<Action>();
//		
//		for (Exit exit : map.locationOf(actor).getExits()) {
//            Location destination = exit.getDestination();
//            if (destination.canActorEnter(actor)) {
//            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
//            }
//        }
//		
//		if (!actions.isEmpty()) {
//			return actions.get(random.nextInt(actions.size()));
//		}
//		else {
//			return null;
//		}
//	}
}
