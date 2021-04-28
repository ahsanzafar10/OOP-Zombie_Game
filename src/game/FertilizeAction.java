package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


/**
 * Action for fertilizing a dirt.
 * 
 * @author HinSeng Leong
 */
public class FertilizeAction extends Action {


	/**
	 * The location needs to be fertilized.
	 */
	protected Location target;
	
	
	/**
	 * Constructor.
	 * 
	 * @param location the location to fertilize
	 */
	public FertilizeAction(Location location) {
		this.target = location;	
	}

	
	/**
	 * Executes a fertilizer on target.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		 Ground a = target.getGround();
		 Crop b = (Crop)a;
		 b.increasetick();
		 return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {

		return actor + " fertilizes dirt";
	}
}
