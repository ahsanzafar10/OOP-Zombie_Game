package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action to quit the game voluntarily
 */

public class QuitAction extends Action {

	/**
	 * Action to remove Player from map.
	 * Once player is removed from map, the engine handles this by exiting the game.
	 * 
	 * @param actor - The actor that is executing this action (It will always be player).
	 * @param map - The map that the actor is on.
	 */
	public String execute(Actor actor, GameMap map) {
		actor.hurt(10000);
		map.removeActor(actor);
		return "User chooses to end the game";
	}

	/**
	 * Menu description to show in console.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Quit Game";
	}
	

}
