package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


/**
 * Action for player to harvest a crop for food.
 * 
 * @author HinSeng Leong
 */
public class PlayerHarvestAction extends Action {

	/**
	 * The location needs to be harvested.
	 */
	protected Location target;
	
	protected String direction;
	
	
	/**
	 * Constructor.
	 * 
	 * @param location the location to harvest
	 * @param direction the direction from the actor
	 */
	public PlayerHarvestAction(Location location, String direction) {
		
		this.target = location;
		this.direction = direction;
	}
	
	
	/**
	 * Executes a harvest on target.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		actor.addItemToInventory(new Food("Food",'='));
		target.setGround(new Dirt());
		return actor + " harvests a " + direction + " ripe crop for food!";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvests a " + direction + " ripe crop";
	}
	

}
