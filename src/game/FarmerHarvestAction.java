package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


/**
 * Action for farmer to harvest a crop for food.
 * 
 * @author HinSeng Leong
 */
public class FarmerHarvestAction extends Action {
	
	
	/**
	 * The location needs to be harvested.
	 */
	protected Location target;
	
	/**
	 * Constructor.
	 * 
	 * @param location the location to harvest
	 */
	public FarmerHarvestAction(Location location) {
		this.target = location;
		
	}
	
	
	/**
	 * Executes a harvest on target.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		target.addItem(new Food("Food",'='));
		target.setGround(new Dirt());
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvests a ripe crop for food!";
	}
}
