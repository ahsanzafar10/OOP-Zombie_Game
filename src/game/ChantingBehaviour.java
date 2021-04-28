package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates ChantingAction for MamboMarie to spawn five new zombies.
 * 
 * @author HinSeng Leong
 *
 */
public class ChantingBehaviour implements Behaviour {

	
	/**
	 * Return a ChantingAction to spawn five new zombies.
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		return new ChantingAction();
	}
	

}
